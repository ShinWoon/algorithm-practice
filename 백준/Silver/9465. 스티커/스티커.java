import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = solve(n, stickers);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int n, int[][] stickers) {
        int[][] dp = new int[2][n];

        // 첫 번째 열
        if (n >= 1) {
            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
        }

        // 두 번째 열
        if (n >= 2) {
            dp[0][1] = Math.max(stickers[0][1], dp[1][0] + stickers[0][1]);
            dp[1][1] = Math.max(stickers[1][1], dp[0][0] + stickers[1][1]);
        }

        // 세 번째 열 ~
        if (n >= 3) {
            for (int i = 2; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    dp[j][i] = Math.max(dp[(j + 1) % 2][i - 1] + stickers[j][i], Math.max(dp[j][i - 1],
                            Math.max(dp[j][i - 2] + stickers[j][i], dp[(j + 1) % 2][i - 2] + stickers[j][i])));
                }
            }
        }

        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}