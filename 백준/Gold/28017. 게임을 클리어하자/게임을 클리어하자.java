import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M;
    private static int[][] timeMap, dp;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        timeMap = new int[N][M];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                timeMap[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        solve();
        int result = Integer.MAX_VALUE;
        // 결과중 최솟값 찾기
        for(int i=0; i<M; i++) {
            result = Math.min(result, dp[N-1][i]);
        }
        System.out.println(result);
    }

    private static void solve(){
        dp = new int[N][M];
        // 첫줄은 다 copy
        for(int i=0; i<M; i++) dp[0][i] = timeMap[0][i];

        for(int i=1; i<N; i++) {
            for(int j=0; j<M; j++) {
                int minNum = Integer.MAX_VALUE;
                for(int k=0; k<M; k++) {
                    if(j == k) continue;    // 이번 선택 무기는 저번에 쓰면 안되니까 빼고 처리
                    minNum = Math.min(minNum, dp[i-1][k]);  // 이전 값 중 최솟값 찾기
                }
                dp[i][j] = timeMap[i][j] + minNum;  // 이번 값 + 저번 최솟값
            }
        }

//        // 마지막 줄 출력
//        for(int i=0; i<M; i++) {
//            System.out.print(dp[N-1][i] + " ");
//        }
//        System.out.println();
    }
}