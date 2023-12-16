import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] numArr = new int[N][];
        String[] tmp;
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            numArr[i] = new int[i+1];
            for(int j=0; j<i+1; j++) numArr[i][j] = Integer.parseInt(tmp[j]);
        }

        int[][] dp = new int[N][];
        dp[0] = new int[1];
        dp[0][0] = numArr[0][0];
        int result = dp[0][0];
        for(int i=1; i<N; i++) {
            dp[i] = new int[i+1];
            for(int j=0; j<i+1; j++) {
                if(j-1 < 0) {
                    dp[i][j] = dp[i-1][j] + numArr[i][j];
                } else if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + numArr[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + numArr[i][j];
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result);
    }
}