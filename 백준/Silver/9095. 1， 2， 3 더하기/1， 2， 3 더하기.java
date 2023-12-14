import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new int[12];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for(int i=1; i<11; i++) {
            dp[i+1] += dp[i];
            if(i+2 <= 11) dp[i+2] += dp[i];
            if(i+3 <= 11) dp[i+3] += dp[i];
        }
        int T = Integer.parseInt(br.readLine());
        for(int test_case=0; test_case<T; test_case++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num]);
        }
    }
}