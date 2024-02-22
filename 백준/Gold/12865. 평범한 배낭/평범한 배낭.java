import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] items = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i][0] = w;
            items[i][1] = v;
        }

        int[][] dp = new int[N+1][100_001];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                int w = items[i-1][0];
                int v = items[i-1][1];

                if(w <= j) {    // 만약에 무게가 이번 조회 무게보다 작으면 이 물건 배낭에 넣을 수 있음
                    int newValue = v + dp[i-1][j-w];    // 새로운 물건의 가치 + (현재 조회 무게 - 새로운 물건 무게)의 가치 
                    if(newValue > dp[i-1][j]) {
                        dp[i][j] = newValue;
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                } else {    // 이전 물건 넣는 것만 되니까
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}