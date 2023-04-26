import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, minCost;
    static boolean[] visited;
    static int[] friendFee;
    static Map<Integer, List<Integer>> relationShip;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(tmp[2]);

        friendFee = new int[N+1];
        tmp = br.readLine().split(" ");
        for(int i=1; i<=N; i++) friendFee[i] = Integer.parseInt(tmp[i-1]);

        relationShip = new HashMap<>();
        for(int i=1; i<=N; i++) relationShip.put(i, new ArrayList<>());
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            relationShip.get(from).add(to);
            relationShip.get(to).add(from);
        }

        if(solve()) System.out.println(minCost);
        else System.out.println("Oh no");
    }

    public static boolean solve() {
        minCost = 0;
        visited = new boolean[N+1];

        // 방문하지 않은 곳을 확인하며 이어져있는 무리 찾기
        for(int i=1; i<=N; i++) {
            if(!visited[i]){
                int currentCost = bfs(i);
                minCost += currentCost;
                K -= currentCost;
                if(K<0) {   // 만약 금액 안에 다 돌지 못하는 경우 친구를 모두 못 사귐
                    return false;
                }
            }
        }
        return true;
    }

    public static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int minCurrentCost = (int)1e9;

        while(!q.isEmpty()) {
            start = q.poll();
            minCurrentCost = Math.min(minCurrentCost, friendFee[start]);
            visited[start] = true;

            for(int i=0; i<relationShip.get(start).size(); i++){
                int next = relationShip.get(start).get(i);
                if(!visited[next]) q.add(next);
            }
        }
        return minCurrentCost;
    }
}