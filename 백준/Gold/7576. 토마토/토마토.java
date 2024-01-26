import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, totalTomatoes, time;
    private static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    private static int[][] box;
    private static boolean[][] visited;
    private static Queue<Integer[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();

        totalTomatoes = 0;
        visited = new boolean[M][N];
        box = new int[M][N];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 0) totalTomatoes++;
                else if(box[i][j] == 1) {
                    q.add(new Integer[]{i,j,0});
                    visited[i][j] = true;
                }
            }
        }

        time = 0;
        solve();
        System.out.println(time);
    }
    private static void solve() {
        int turn = 0;
        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int y = info[0];
            int x = info[1];
            turn = info[2];

            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if(cx<0 || cx>=N || cy<0 || cy>=M) continue;
                if(box[cy][cx] == 0 && !visited[cy][cx]) {
                    q.add(new Integer[]{cy,cx,turn + 1});
                    totalTomatoes--;
                    visited[cy][cx] = true;
                }
            }
        }
        if(totalTomatoes == 0) {
            time = turn;
        } else {
            time = -1;
        }
    }
}