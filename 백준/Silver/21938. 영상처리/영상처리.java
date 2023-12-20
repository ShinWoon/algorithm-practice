import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, T, RESULT;
    private static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    private static int[][] screen;
    private static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        screen = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                screen[i][j] = (r+g+b)/3;   // rgb 평균 값 저장
            }
        }

        T = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(screen[i][j] >= T) screen[i][j] = 255;    // 평균값 >= 경계값 T라면 255로 저장
                else screen[i][j] = 0;
            }
        }

        RESULT = 0;
        visited = new boolean[N][M];
        // 255 픽셀인 물체 수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(screen[i][j] == 255 && !visited[i][j]) {
                    visited[i][j] = true;
                    solve(i, j);
                    RESULT++;
                }
            }
        }
        System.out.println(RESULT);
    }

    private static void solve(int y, int x) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{y,x});

        while(!q.isEmpty()) {
            Integer[] xy = q.poll();
            y = xy[0];
            x = xy[1];

            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
                if(screen[cy][cx] == 255 && !visited[cy][cx]) {
                    visited[cy][cx] = true;
                    q.add(new Integer[]{cy,cx});
                }
            }
        }
    }
}