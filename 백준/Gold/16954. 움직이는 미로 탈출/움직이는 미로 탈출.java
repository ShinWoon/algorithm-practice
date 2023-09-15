import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static char[][] map;
    private static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1, 0}, dy = {0, -1, 0, 1, -1, 1, -1, 1, 0}; // 상하좌우, 대각선, 제자리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            String inputStr = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        if (solve()) System.out.println(1);
        else System.out.println(0);
    }

    private static boolean solve() {
        int y = 7;
        int x = 0;
        int time = 0;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{y, x, time});  // 처음은 가장 왼쪽 아래
        // 왔던 곳 다시 가야할 수 있기 때문에 visited 처리 X
        
        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            y = info[0];
            x = info[1];
            int currentTime = info[2];

            if (time < currentTime) {
                // 1 초 지났음
                setMap();
                time++;
            }
            if (map[y][x] == '#') {
                continue;
            } else if (y == 0 && x == 7) {
                // 도착지에 도달
                return true;
            }
            for (int i = 0; i < 9; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if (cx < 0 || cx >= 8 || cy < 0 || cy >= 8) continue;
                if (map[cy][cx] == '.') {
                    q.add(new Integer[]{cy, cx, currentTime + 1});
                }
            }
        }
        return false;
    }

    private static void setMap() {
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
        Arrays.fill(map[0], '.');
    }
}