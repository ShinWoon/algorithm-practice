import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> lightRelations = new HashMap<>();   // 나보다 가벼운 애들 관계 찾기 위해
        Map<Integer, List<Integer>> heavyRelations = new HashMap<>();   // 나보다 무거운 애들 관계 찾기 위해
        for(int i=1; i<=N; i++) {
            lightRelations.put(i, new ArrayList<>());
            heavyRelations.put(i, new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            String[] tmp = br.readLine().split(" ");
            int heavy = Integer.parseInt(tmp[0]);
            int light = Integer.parseInt(tmp[1]);
            lightRelations.get(heavy).add(light);
            heavyRelations.get(light).add(heavy);
        }

        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            solve(i, lightRelations);   // 나보다 가벼운 애들 다 조사
            solve(i, heavyRelations);   // 나보다 무거운 애들 다 조사
            cntUnknown();               // 이번 물건이 무게를 조회할 수 없는 물건은?
        }
    }

    private static void solve(int start, Map<Integer, List<Integer>> relations) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int currentStuff = q.poll();
            visited[currentStuff] = true;

            for(int i=0; i<relations.get(currentStuff).size(); i++) {
                int nextStuff = relations.get(currentStuff).get(i);
                if(!visited[nextStuff]) {
                    q.add(nextStuff);
                }
            }
        }
    }

    private static void cntUnknown() {
        int cntStuff = 0;
        for(int i=1; i<=N; i++) {
            if(!visited[i]) cntStuff++;
        }
        System.out.println(cntStuff);
    }
}