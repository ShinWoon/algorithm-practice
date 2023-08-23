import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, carriageNum;
    private static int[] trainPassenger, sumArr;
    private static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        trainPassenger = new int[N];
        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            trainPassenger[i] = Integer.parseInt(tmp[i]);
        }

        carriageNum = Integer.parseInt(br.readLine());
        solve();
        System.out.println(dp[2][N-1]);
    }

    private static void solve() {
        sumArr = new int[N];
        sumArr[0] = trainPassenger[0];
        for(int i=1; i<N; i++) {
            sumArr[i] = sumArr[i-1] + trainPassenger[i];
        }

//        for(int i=0; i<N; i++) System.out.print(sumArr[i] + " ");

        dp = new int[3][N];
        int maxNum = 0;
        for(int i=carriageNum-1; i<N; i++) {
            if(i-carriageNum<0) dp[0][i] = sumArr[i];
            else dp[0][i] = Math.max(maxNum, sumArr[i]-sumArr[i-carriageNum]);
            maxNum = Math.max(maxNum, dp[0][i]);
        }
//        for(int i=0; i<N; i++) System.out.print(dp[0][i] + " ");
        for(int i=1; i<3; i++) {
            for(int j=i*carriageNum; j<N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-carriageNum] + (sumArr[j]-sumArr[j-carriageNum]));
            }
        }
//        for(int i=0; i<N; i++) System.out.print(dp[2][i] + " ");
    }
}