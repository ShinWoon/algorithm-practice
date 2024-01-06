import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][N+1];
        setDp();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int result = dp[y2][x2] - dp[y2][x1-1] - dp[y1-1][x2] + dp[y1-1][x1-1];
            System.out.println(result);
        }
    }

    private static void setDp() {
        // 가로 dp
        for(int i=1; i<=N; i++) {
            dp[i][1] = map[i][1];
            for(int j=2; j<=N; j++) {
                dp[i][j] = dp[i][j-1] + map[i][j];
            }
        }

        // 세로 dp
        for(int i=1; i<=N; i++) {
            for(int j=2; j<=N; j++) {
                dp[j][i] += dp[j-1][i];
            }
        }
//        for(int i=1; i<=N; i++) {
//            for(int j=1; j<=N; j++) System.out.print(dp[i][j] + " ");
//            System.out.println();
//        }
    }
}