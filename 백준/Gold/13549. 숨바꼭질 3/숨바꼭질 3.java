import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = solve();
        System.out.println(result);
    }
    private static int solve() {
        int cnt = 0;
        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });
        q.add(new Integer[]{N, cnt});
        boolean[] visited = new boolean[100001];
        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int current = info[0];
            cnt = info[1];
            visited[current] = true;
            if(current == K) {
                break;
            }
            if(0 <= current-1 && !visited[current - 1]) {
                q.add(new Integer[]{current - 1, cnt+1});
            }
            if(current + 1 <= 100000 && !visited[current+1]) {
                q.add(new Integer[]{current+1, cnt+1});
            }
            if(current * 2 <= 100000 && !visited[current*2]) {
                q.add(new Integer[]{current*2, cnt});
            }
        }
        return cnt;
    }
}