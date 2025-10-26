import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, minR, minC, maxR, maxC;
    private static char[][] map, newMap;
    private static int[] dx={0,-1,0,1}, dy={-1,0,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        minR = R;
        minC = C;
        maxR = 0;
        maxC = 0;
        newMap = new char[R][C];
        solve();

        StringBuilder sb = new StringBuilder();
        for(int i=minR; i<=maxR; i++) {
            for(int j=minC; j<=maxC; j++) {
                sb.append(newMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static void solve() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newMap[i][j] = map[i][j];
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int cx = j + dx[k];
                        int cy = i + dy[k];
                        if (cx < 0 || cx >= C || cy < 0 || cy >= R) {
                            cnt++;
                            continue;
                        }
                        if (map[cy][cx] == '.') cnt++;
                    }
                    if (cnt < 3) {
                        minR = Math.min(minR, i);
                        maxR = Math.max(maxR, i);
                        minC = Math.min(minC, j);
                        maxC = Math.max(maxC, j);
                    } else {
                        newMap[i][j] = '.';
                    }
                }
            }
        }
    }
}
