import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static boolean[] visited;
    private static int N, M, result;
    private static int[] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new int[101];
        for(int i=1; i<=100; i++) {
            map[i] = i;
        }

        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            map[from] = to;
        }

        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            map[from] = to;
        }

        visited = new boolean[101];
        result = Integer.MAX_VALUE;
        solve();
        System.out.println(result);
    }

    private static void solve() {
        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1]-o2[1];
            }
        });
        q.add(new Integer[]{1,0});

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            int start = info[0];
            int cnt = info[1];

            if(start == 100) {
                result = Math.min(result, cnt);
                continue;
            }
            for(int i=1; i<=6; i++) {
                int next = start + i;
                if(next > 100) continue;
                if(!visited[map[next]]) {
                    visited[map[next]] = true;
                    q.add(new Integer[]{map[next], cnt+1});
                }
            }
        }


    }
}