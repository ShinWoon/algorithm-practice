import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static int N, M, K, RESULT;
    private static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    private static int[][] map, spot;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(tmp[2]);
        RESULT = -1;

        map = new int[N+1][M+1];
        spot = new int[K][2];
        for(int i=0; i<K; i++) {
            tmp = br.readLine().split(" ");
            int r = Integer.parseInt(tmp[0]);
            int c = Integer.parseInt(tmp[1]);
            map[r][c] = 1;
            spot[i][0] = r;
            spot[i][1] = c;
        }

        solve();
        System.out.println(RESULT);
    }

    private static void solve() {
        visited = new boolean[N+1][M+1];

        for(int i=0; i<spot.length; i++) {
            int y = spot[i][0];
            int x = spot[i][1];
            if(!visited[y][x]) {
                int result = bfs(y,x);
                RESULT = Math.max(result, RESULT);
            }
        }
    }

    private static int bfs(int y, int x) {
        int cnt = 0;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{y,x});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            y = info[0];
            x = info[1];

            cnt++;
            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if(cx<0 || cx>M || cy<0 || cy>N) continue;
                if(!visited[cy][cx] && map[cy][cx] == 1) {
                    q.add(new Integer[]{cy,cx});
                    visited[cy][cx] = true;
                }
            }
        }
        return cnt;
    }
}