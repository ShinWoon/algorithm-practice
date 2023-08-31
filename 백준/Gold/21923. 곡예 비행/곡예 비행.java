import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N, M, answer;
    private static int[] dx = {1, 0, -1}, dy = {0, -1, 0};  // 오, 위, 왼
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        solve();
        System.out.println(answer);
    }

    private static void solve() {
        // 상승 비행
        // 시작점은 (0,N-1)
        int x = 0;
        int y = N - 1;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{x, y});
        int[][] flightUp = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) flightUp[i][j] = -Integer.MAX_VALUE;
        }
        flightUp[y][x] = map[y][x];

        // 상승 비행 중에는 앞 또는 위로만 이동
        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            x = info[0];
            y = info[1];

            for (int i = 0; i < 2; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if (cx >= M || cy < 0) continue;
                if (flightUp[cy][cx] < flightUp[y][x] + map[cy][cx]) {
                    flightUp[cy][cx] = flightUp[y][x] + map[cy][cx];
                    q.add(new Integer[]{cx, cy});
                }
            }
        }

//        for(int i=N-2; i>=0; i--) flightUp[i][0] = flightUp[i+1][0] + map[i][0];
//        for(int i=1; i<M; i++) flightUp[N-1][i] = flightUp[N-1][i-1] + map[N-1][i];
//        for(int i=1; i<M; i++) {
//            for(int j=N-2; j>=0; j--) {
//                flightUp[j][i] = Math.max(flightUp[j][i-1]+map[j][i], flightUp[j+1][i] + map[j][i]);
//            }
//        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(flightUp[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 하강 비행
        // 끝점은 (M-1, N-1)
        x = M - 1;
        y = N - 1;
        q.add(new Integer[]{x, y});
        int[][] flightDown = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) flightDown[i][j] = -Integer.MAX_VALUE;
        }
        flightDown[y][x] = map[y][x];

        // 하강 비행 중
        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            x = info[0];
            y = info[1];

            for (int i = 1; i < 3; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if (cx < 0 || cy < 0) continue;
                if (flightDown[cy][cx] < flightDown[y][x] + map[cy][cx]) {
                    flightDown[cy][cx] = flightDown[y][x] + map[cy][cx];
                    q.add(new Integer[]{cx, cy});
                }
            }
        }

//        for(int i=N-2; i>=0; i--) flightDown[i][M-1] = flightDown[i+1][M-1] + map[i][M-1];
//        for(int i=M-2; i>=0; i--) flightDown[0][i] = flightDown[0][i+1] + map[0][i];
//
//        for(int i=M-2; i>=0; i--) {
//            for(int j=1; j<N; j++) {
//                flightDown[j][i] = Math.max(flightDown[j-1][i] + map[j][i], flightDown[j][i+1] + map[j][i]);
//            }
//        }
//        System.out.println("-------------------------");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(flightDown[i][j] + " ");
//            }
//            System.out.println();
//        }

        answer = -Integer.MAX_VALUE;    // 최대 합 음수일 수 있으니까
        // 상승, 하강 합 중 최대
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                answer = Math.max(answer, flightUp[i][j] + flightDown[i][j]);
            }
        }
    }
}