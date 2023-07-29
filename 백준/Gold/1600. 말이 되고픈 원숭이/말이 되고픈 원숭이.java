import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static class Info {
        int x;
        int y;
        int kCnt;
        int move;

        public Info(int x, int y, int kCnt, int move) {
            this.x = x;
            this.y = y;
            this.kCnt = kCnt;
            this.move = move;
        }
    }

    private static int K, W, H, result;
    private static int[] monkeyDx = {0, -1, 0, 1}, monkeyDy = {-1, 0, 1, 0}, horseDx = {-1, 1, -2, 2, -2, 2, -1, 1}, horseDy = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        String[] tmp = br.readLine().split(" ");
        W = Integer.parseInt(tmp[0]);
        H = Integer.parseInt(tmp[1]);

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        result = Integer.MAX_VALUE;
        solve();
        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    // (0,0) -> (W-1,H-1)로 이동
    private static void solve() {
        Queue<Info> q = new LinkedList<>();
        int x = 0;
        int y = 0;
        int kCnt = 0;
        int move = 0;
        q.add(new Info(x, y, kCnt, move));    // (x,y,말 방식 이동 수, 원숭이 동작수)
        visited = new boolean[H][W][K+1];
//        setMap();
        visited[y][x][kCnt] = true;

        while (!q.isEmpty()) {
            Info info = q.poll();
            x = info.x;
            y = info.y;
            kCnt = info.kCnt;
            move = info.move;

//            System.out.println("x " + x + " y " + y);
            if ((x == W - 1) && (y == H - 1)) {
                result = move;
                return;
            }
            // 원숭이 방식 이동
            for (int i = 0; i < 4; i++) {
                int cx = x + monkeyDx[i];
                int cy = y + monkeyDy[i];
                if (cx < 0 || cx >= W || cy < 0 || cy >= H) continue;
                if (!visited[cy][cx][kCnt] && map[cy][cx] != 1) {
                    q.add(new Info(cx, cy, kCnt, move + 1));
                    visited[cy][cx][kCnt] = true;
                }
            }
            // 말 방식 이동
            if (kCnt < K) {
                for (int i = 0; i < 8; i++) {
                    int cx = x + horseDx[i];
                    int cy = y + horseDy[i];
                    if (cx < 0 || cx >= W || cy < 0 || cy >= H) continue;
                    if (!visited[cy][cx][kCnt+1] && map[cy][cx] != 1) {
                        q.add(new Info(cx, cy, kCnt + 1, move + 1));
                        visited[cy][cx][kCnt+1] = true;
                    }
                }
            }
        }
    }

//    private static void setMap() {
//        for (int i = 0; i < H; i++) {
//            Arrays.fill(visited[i], Integer.MAX_VALUE);
//        }
//    }
}