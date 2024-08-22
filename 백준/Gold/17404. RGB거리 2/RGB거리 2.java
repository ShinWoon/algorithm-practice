import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] rgbCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        rgbCost = new int[N][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) rgbCost[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int[][] dp = new int[N][3];
        for(int i=0; i<3; i++) {

            for(int col=0; col<3; col++) {
                if(col == i) dp[0][col] = rgbCost[0][col];
                else dp[0][col] = 1_000_000;
            }

            for(int row=1; row<N; row++) {
                dp[row][0] = Math.min(dp[row-1][1], dp[row-1][2]) + rgbCost[row][0];
                dp[row][1] = Math.min(dp[row-1][0], dp[row-1][2]) + rgbCost[row][1];
                dp[row][2] = Math.min(dp[row-1][0], dp[row-1][1]) + rgbCost[row][2];
            }

            for(int col=0; col<3; col++) {
                if(col != i) answer = Math.min(answer, dp[N-1][col]);
            }
        }

        System.out.println(answer);
    } 
}