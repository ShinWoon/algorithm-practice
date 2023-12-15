import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] tmp = br.readLine().split(" ");
        int[] A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(tmp[i]);
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int result = 1;
        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}