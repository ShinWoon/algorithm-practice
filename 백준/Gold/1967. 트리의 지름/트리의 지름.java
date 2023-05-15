import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int N, maxDiameter, startNode, maxTotalWeight;
    static Map<Integer, List<Integer[]>>  childrenInfo;
    static Map<Integer, Integer[]> parentInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parentInfo = new HashMap<>();
        childrenInfo = new HashMap<>();
        // node info setting
        for (int i = 1; i <= N; i++) {
            childrenInfo.put(i, new ArrayList<>());
        }

        // 간선 정보 받기
        for (int i = 0; i < N - 1; i++) {
            String[] tmp = br.readLine().split(" ");
            int parent = Integer.parseInt(tmp[0]);
            int child = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);

            childrenInfo.get(parent).add(new Integer[]{child, weight});  // 자식 정보에 자식 노드 번호랑 가중치 입력
            parentInfo.put(child, new Integer[]{parent, weight});
        }

        solve();
        System.out.println(maxDiameter);
    }

    private static void solve() {
        // 1번 노드부터 쭉쭉 내려가면서 leaf node에 도달하면 거기까지의 total weight 값을 leafNodeLength에 저장
        maxTotalWeight = 0;
        maxDiameter = 0;
        if(N == 1) return;
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0);
//        System.out.println(startNode + " " + maxTotalWeight);
        // leaf node 2개 선택해서 트리의 지름 구하기
//        for(int i=0; i<leafNode.size(); i++) System.out.println(leafNode.get(i));
        visited = new boolean[N+1];
        getMaxDiameter(startNode);
    }

    private static void dfs(int start, int totalweight) {
        if(childrenInfo.get(start).size() == 0) {
            // leaf node라면
            if(maxTotalWeight < totalweight) {
                maxTotalWeight = totalweight;
                startNode = start;
            }
            return;
        }

        for(int i=0; i<childrenInfo.get(start).size(); i++) {
            Integer[] info = childrenInfo.get(start).get(i);
            int nextNode = info[0];
            int weight = info[1];
            if(!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(nextNode, totalweight + weight);
                visited[nextNode] = false;
            }
        }

    }

    private static void getMaxDiameter(int start) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{start, 0});
        visited[start] = true;

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            int nextNode = info[0];
            int totalWeight = info[1];

            maxDiameter = Math.max(maxDiameter, totalWeight);
            if(parentInfo.containsKey(nextNode)) {
                Integer[] nextInfo = parentInfo.get(nextNode);
                if(!visited[nextInfo[0]]) {
                    visited[nextInfo[0]] = true;
                    q.add(new Integer[]{nextInfo[0], totalWeight + nextInfo[1]});
                }
            }
            for(int i=0; i<childrenInfo.get(nextNode).size(); i++) {
                Integer[] nextInfo = childrenInfo.get(nextNode).get(i);
                if(!visited[nextInfo[0]]){
                    visited[nextInfo[0]] = true;
                    q.add(new Integer[]{nextInfo[0], totalWeight + nextInfo[1]});
                }
            }
        }
    }
}