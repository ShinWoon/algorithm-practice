import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N][2][3];
        for (int i=0; i<N; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        int result = solution(0,0,0);
        System.out.println(result);
    }
    private static int solution(int n, int late, int absent) {
        if(late == 2 || absent == 3) {  // 지각 2번 이상, 결석 연속 3번 불가능
            return 0;
        }
        if(n == N) {    // 모든 경우 다 되면
            return 1;
        }
        if(dp[n][late][absent] >= 0) return dp[n][late][absent] % 1_000_000;    // 이미 앞에서 값이 결정되었으면 바로 리턴
        // 정상 출석 -> 연속 결석률 0, 지각 -> 지각+1, 연속 결석률 0, 결석 -> 연속 결석 + 1
        dp[n][late][absent] = solution(n+1, late, 0)
                + solution(n+1, late+1, 0)
                + solution(n+1, late, absent+1);
        return dp[n][late][absent] % 1_000_000;
    }
}