import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean check;   // 원웅이가 파이프 연결 성공했는지 확인 변수
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
        int[][] spots = new int[C][2];  // 원웅이가 파이프를 설치하면서 들렸던 곳들 저장 배열
        for(int i=0; i<R; i++) {
            // 열마다 돌아보기
            check = false;  // 열마다 설치 확인 -> check를 false로 초기화
            dfs(0, i, spots);
        }
    }

    private static void dfs(int x, int y, int[][] spots) {
        if(x == (C-1)) {    // 만약 마지막 열까지 갔다면
            totalLine += 1;
            setMap(spots);  // 내가 지금까지 지나온 곳 지도에 표시 해주기. 파이프는 겹칠 수 없으니까.
            check = true;   // 파이프 섪치 성공 알려주기
            return;
        }

        // 3가지 방법으로 갈 수 있음. 조회
        for(int i=0; i<3; i++) {
            if(check) return;   // 성공했으니 돌아가기만 하자.
            int cx = x + dx[i];
            int cy = y + dy[i];
            if(cx>=C || cy<0 || cy>=R) continue;
            if(map[cy][cx] == '.' && !visited[cy][cx]) {    // 집이 아니고 방문 안한 경우만
                visited[cy][cx] = true;
                spots[cx][0] = cx;
                spots[cx][1] = cy;
                dfs(cx, cy, spots);
//                visited[cy][cx] = false;  // 이거 하나로 합불?? 왜지...?
            }
        }
    }
    private static void setMap(int[][] spots) {
        // 파이프가 설치된 곳은 집으로 처리해버리기
        for(int i=0; i<C; i++) {
            map[spots[i][1]][spots[i][0]] = 'X';
        }
    }
}