import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, X;
    private static Map<Integer, List<Integer>> beforeMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        beforeMap = new HashMap<>();
        for(int i=1; i<=N; i++) beforeMap.put(i, new ArrayList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            beforeMap.get(end).add(start);
        }

        X = Integer.parseInt(br.readLine());
        int result = solve();
        System.out.println(result);
    }
    private static int solve() {
        int current = X;
        int depth = 0;
        int cnt = -1;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{current, depth});
        boolean[] visited = new boolean[N+1];
        visited[X] = true;

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            current = info[0];
            depth = info[1];
            cnt++;  // 해야하는 일을 모두 다 계산해야 하기 때문에 depth를 쓰면 안됨

            for(int i=0; i<beforeMap.get(current).size(); i++) {
                int next = beforeMap.get(current).get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(new Integer[]{next, depth+1});
                }
            }
        }
        return cnt;
    }
}