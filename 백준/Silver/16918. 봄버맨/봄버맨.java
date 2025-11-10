import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, N;
    private static int[] dx={-1,0,1,0}, dy={0,-1,0,1};
    private static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'B') {
                    sb.append('O');
                } else {
                    sb.append(map[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static void solve() {
        int time = 0;
        while(time < N) {
            time++;
            if(time%2 == 0) {
                fillBomb();
            } else {
                map = boom();
            }
        }
    }
    private static void fillBomb() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == '.') {
                    map[i][j] = 'O';
                } else {
                    map[i][j] = 'B';
                }
            }
        }
    }
    private static char[][] boom() {
        char[][] newMap = new char[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(newMap[i][j] == 0) {
                    // 앞에 폭탄 터진 주변임을 저장했을 때 덮어쓰지 않기 위함
                    newMap[i][j] = map[i][j];
                }
                if(map[i][j] == 'B') {
                    for(int k=0; k<4; k++) {
                        int cx = j + dx[k];
                        int cy = i + dy[k];
                        if(cx<0 || cx>=C || cy<0 || cy>=R) continue;
                        newMap[cy][cx] = '.';
                    }
                    // 폭탄있던 자리도 빈걸로 바꾸기
                    newMap[i][j] = '.';
                }
            }
        }
        return newMap;
    }
}