import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean check;
    static int R, C, totalLine;
    static int[] dx = {1,1,1}, dy = {-1,0,1};  // 오른쪽 위, 오른쪽, 오른쪽 아래
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        map = new char[R][C];
        String input;
        for(int i=0; i<R; i++) {
            input = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        solve();
        System.out.println(totalLine);
    }

    private static void solve() {
        totalLine = 0;
        visited = new boolean[R][C];
        int[][] spots = new int[C][2];
        for(int i=0; i<R; i++) {
            // 열마다 돌아보기
            check = false;
            dfs(0, i, spots);
        }
    }

    private static void dfs(int x, int y, int[][] spots) {
        if(x == (C-1)) {
            totalLine += 1;
            setMap(spots);
            check = true;
            return;
        }

        // 3가지 방법으로 갈 수 있음. 조회
        for(int i=0; i<3; i++) {
            if(check) return;
            int cx = x + dx[i];
            int cy = y + dy[i];
            if(cx>=C || cy<0 || cy>=R) continue;
            if(map[cy][cx] == '.' && !visited[cy][cx]) {
                visited[cy][cx] = true;
                spots[cx][0] = cx;
                spots[cx][1] = cy;
                dfs(cx, cy, spots);
//                visited[cy][cx] = false;
            }
        }
    }
    private static void setMap(int[][] spots) {
        for(int i=0; i<C; i++) {
            map[spots[i][1]][spots[i][0]] = 'X';
        }
    }
}