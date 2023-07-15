import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean check;
    private static boolean[][] visited, goSafeZone;
    private static int N, M, result;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String inputStr = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        solve();
        System.out.println(result);
    }

    private static void solve() {
        visited = new boolean[N][M];
        goSafeZone = new boolean[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
//                    check = false;
                    dfs(i, j);
                }
            }
        }
    }

    private static void dfs(int y, int x) {
        int cx, cy;

        if(map[y][x] == 'D') {
            cx = x;
            cy = y+1;
        } else if(map[y][x] == 'U') {
            cx = x;
            cy = y-1;
        } else if(map[y][x] == 'R') {
            cx = x+1;
            cy = y;
        } else {    // L
            cx = x-1;
            cy = y;
        }

        if(!visited[cy][cx]) {
            visited[cy][cx] = true;
            dfs(cy, cx);
        } else {
            if(!goSafeZone[cy][cx]) {
                result++;
            }
        }
        goSafeZone[y][x] = true;
    }

//    private static boolean checkSafeZone(int y, int x) {
//        if(map[y][x] == 'D') {
//            return goSafeZone[y+1][x];
//        } else if(map[y][x] == 'U') {
//            return goSafeZone[y-1][x];
//        } else if(map[y][x] == 'R') {
//            return goSafeZone[y][x+1];
//        } else {    // L
//            return goSafeZone[y][x-1];
//        }
//    }
}