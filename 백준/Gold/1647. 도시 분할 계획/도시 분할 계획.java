import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int town1;
        int town2;
        int cost;

        public Node(int town1, int town2, int cost) {
            this.town1 = town1;
            this.town2 = town2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int N, M;
    static int[] parent;
    static Queue<Node> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        graph = new PriorityQueue<>();
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int town1 = Integer.parseInt(tmp[0]);
            int town2 = Integer.parseInt(tmp[1]);
            int cost = Integer.parseInt(tmp[2]);
            graph.add(new Node(town1, town2, cost));
        }

        int result = solve();
        System.out.println(result);
    }

    private static int solve() {
        List<Integer> costList = new ArrayList<>();

        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;  // makeSet

        // kruskal
        while(true) {
            if(graph.isEmpty()) break;
            Node thisNode = graph.poll();
            if(find(thisNode.town1) != find(thisNode.town2)) {
                costList.add(thisNode.cost);
                union(thisNode.town1, thisNode.town2);
            }
        }

        // 현재 모든 마을 내에 모든 집들이 최소 비용으로 연결 -> 마을 2개로 만들기
        int totalCost = 0;
        for(int i=0; i<costList.size()-1; i++) {    // 길이 긴거 빼고 마을 하나 만들기 -> 마을 2개 만들기 됨
            totalCost += costList.get(i);
        }
        return totalCost;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x<y) parent[y] = x;
        else parent[x] = y;
    }
    private static int find(int x) {
        if(parent[x] == x) return x;
        else return find(parent[x]);
    }
}