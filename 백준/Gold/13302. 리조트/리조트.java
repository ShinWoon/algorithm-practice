import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static boolean[] date;
    private static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        date = new boolean[N+1];
        if(M != 0) st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            date[num] = true;
        }

        dp = new int[N+1][N+1];
        for(int i=1; i<=N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

        int result = solution(1,0);
        System.out.println(result);
    }
    private static int solution(int n, int coupon) {
        if(n > N) {
            return 0;
        }
        if(dp[n][coupon] < Integer.MAX_VALUE) return dp[n][coupon];

        if(date[n]) {  // 못 가는 날짜면
            dp[n][coupon] = Math.min(dp[n][coupon], solution(n+1, coupon));
            return dp[n][coupon];
        }

        if(coupon >= 3) {   // 쿠폰 사용
            dp[n][coupon] = Math.min(dp[n][coupon], solution(n+1, coupon - 3));
        }
        // 하루권 구매 10,000
        dp[n][coupon] = Math.min(dp[n][coupon], solution(n+1, coupon) + 10_000);
        // 3일권 구매 25,000 쿠폰 1장
        dp[n][coupon] = Math.min(dp[n][coupon], solution(n+3, coupon + 1) + 25_000);
        // 5일권 구매 37,000 쿠폰 2장
        dp[n][coupon] = Math.min(dp[n][coupon], solution(n+5, coupon+2) + 37_000);

        return dp[n][coupon];
    }
}