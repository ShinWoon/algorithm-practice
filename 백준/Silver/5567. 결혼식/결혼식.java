import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, RESULT;
    private static boolean[] visited;
    private static Map<Integer, List<Integer>> relation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        relation = new HashMap<>();
        for(int i=1; i<=N; i++) relation.put(i, new ArrayList<>());
        for(int i=0; i<M; i++) {
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            relation.get(a).add(b);
            relation.get(b).add(a);
        }
        solve();
        System.out.println(RESULT);
    }

    private static void solve() {
        RESULT = 0;
        visited = new boolean[N+1];
        visited[1] = true;
        Queue<Integer[]> q = new LinkedList<>();
        for(int i=0; i<relation.get(1).size(); i++) {
            int next = relation.get(1).get(i);
            q.add(new Integer[]{next, 1});
            visited[next] = true;
        }

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int current = info[0];
            int currentDepth = info[1];

            if(currentDepth <= 2) {
                RESULT++;
            } else {
                continue;
            }
            for(int i=0; i<relation.get(current).size(); i++) {
                int next = relation.get(current).get(i);
                if(!visited[next]){
                    q.add(new Integer[]{next, currentDepth+1});
                    visited[next] = true;
                }
            }
        }
    }
}