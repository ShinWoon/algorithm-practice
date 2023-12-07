import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Info {
        int v;
        int w;

        public Info(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private static int V, E, K;
    private static int[] distance;
    private static boolean[] visited;
    private static Map<Integer, List<Info>> relation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tmp = br.readLine().split(" ");
        V = Integer.parseInt(tmp[0]);
        E = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(br.readLine());

        relation = new HashMap<>();
        for(int i=1; i<=V; i++) {
            relation.put(i, new ArrayList<>());
        }

        for(int i=0; i<E; i++) {
            tmp = br.readLine().split(" ");
            int u = Integer.parseInt(tmp[0]);
            int v = Integer.parseInt(tmp[1]);
            int w = Integer.parseInt(tmp[2]);
            relation.get(u).add(new Info(v,w));
        }
        solve();

        for(int i=1; i<=V; i++) {
            if(distance[i] != Integer.MAX_VALUE) sb.append(distance[i]).append("\n");
            else sb.append("INF\n");    // 못가는 애들
        }
        System.out.print(sb);
    }
    private static void solve() {
        visited = new boolean[V+1];
        distance = new int[V+1];
        for(int i=1; i<=V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[K] = 0; // 본인이면

        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });
        q.add(new Integer[]{K, 0});

        while(!q.isEmpty()) {
            Integer[] arr = q.poll();
            int current = arr[0];
            int weight = arr[1];

            if(visited[current]) continue;
            visited[current] = true;

            if(distance[current] < weight) continue;
            for(int i=0; i<relation.get(current).size(); i++) {
                Info info = relation.get(current).get(i);
                int next = info.v;
                int nextWeight = info.w;
                if(distance[next] > weight + nextWeight) {
                    distance[next] = weight + nextWeight;
                    q.add(new Integer[]{next, distance[next]});
                }
            }
        }
    }
}