import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static boolean check;
    private static boolean[] visited;
    private static int N, M;
    private static Map<Integer, List<Integer>> relation;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        relation = new HashMap<>();
        for(int i=0; i<N; i++) relation.put(i, new ArrayList<>());

        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            relation.get(from).add(to);
            relation.get(to).add(from);
        }

        solve();
    }

    private static void solve() {
        check = false;

        for(int i=0; i<N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, 0);
            if(check) break;
        }

        if(check) System.out.println("1");
        else System.out.println("0");
    }

    private static void dfs(int start, int level) {
        if(level == 4) {
            // A -> B -> C -> D <= 이런 관계
            check = true;
            return;
        }

        for(int i=0; i<relation.get(start).size(); i++) {
            int next = relation.get(start).get(i);
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, level+1);
                visited[next] = false;
            }
        }
    }
}