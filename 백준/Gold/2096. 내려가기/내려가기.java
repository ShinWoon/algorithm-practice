import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, TOTALMAX, TOTALMIN;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TOTALMAX = -1;
        TOTALMIN = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];

        for(int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        solve();
        System.out.println(TOTALMAX + " " + TOTALMIN);
    }

    private static void solve() {
        int[][] minMap = new int[N][3];
        int[][] maxMap = new int[N][3];
        for(int i=0; i<3; i++) {
            minMap[0][i] = map[0][i];
            maxMap[0][i] = map[0][i];
        }
        for(int i=1; i<N; i++) {    // 2번째 줄부터 시작
            minMap[i][0] = Math.min(minMap[i-1][0], minMap[i-1][1]) + map[i][0];
            minMap[i][1] = Math.min(minMap[i-1][0], Math.min(minMap[i-1][1], minMap[i-1][2])) + map[i][1];
            minMap[i][2] = Math.min(minMap[i-1][1], minMap[i-1][2]) + map[i][2];

            maxMap[i][0] = Math.max(maxMap[i-1][0], maxMap[i-1][1]) + map[i][0];
            maxMap[i][1] = Math.max(maxMap[i-1][0], Math.max(maxMap[i-1][1], maxMap[i-1][2])) + map[i][1];
            maxMap[i][2] = Math.max(maxMap[i-1][1], maxMap[i-1][2]) + map[i][2];
        }
        for(int i=0; i<3; i++) {
            TOTALMIN = Math.min(TOTALMIN, minMap[N-1][i]);
            TOTALMAX = Math.max(TOTALMAX, maxMap[N-1][i]);
        }
    }
}