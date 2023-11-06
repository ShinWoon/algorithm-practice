import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, K, S;
    private static boolean[][] map;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        map = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            map[a][b] = true;
        }
        solve();
        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            if (map[a][b]) sb.append("-1\n");
            else if (map[b][a]) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][k] && map[k][j]) {
                        // 둘 다 true 일 때
                        map[i][j] = true;
                    }
                }
            }
        }

//        for(int i=1; i<=N; i++) {
//            for(int j=1; j<=N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}