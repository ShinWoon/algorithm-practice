import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N];
        for(int i=0; i<N; i++) Arrays.fill(dp[i], -1);
        System.out.println(dfs(0,0));   // (N-1, N-1) 도달 가능 수
    }
    private static long dfs(int y, int x) {
        if(y == N-1 && x == N-1) return 1;
        if(dp[y][x] != -1) return dp[y][x];
        if(map[y][x] == 0) return 0;

        dp[y][x] = 0;
        int currentNum = map[y][x];
        // 오른쪽으로 점프
        int cx = x + currentNum;
        if(cx < N) {
            dp[y][x] += dfs(y, cx);
        }
        // 아래로 점프
        int cy = y + currentNum;
        if(cy < N) {
            dp[y][x] += dfs(cy, x);
        }

        return dp[y][x];
    }
}
