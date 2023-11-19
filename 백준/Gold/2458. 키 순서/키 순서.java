import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M, RESULT;
    private static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new boolean[N+1][N+1];
        for(int i=1; i<=N; i++) map[i][i] = true;
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            map[a][b] = true;
        }

        RESULT = 0;

        solve();
//        for(int i=1; i<=N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
        // 자기 순서 아는 애들 찾기
        find();
        System.out.println(RESULT);
    }

    private static void solve() {
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }
    }

    private static void find() {
        boolean check = true;
        for(int i=1; i<=N; i++) {
            check = true;
            for(int j=1; j<=N; j++) {
                if(!map[j][i]) {
                    // 값이 false일 때
                    if(!map[i][j]) {
                        // 여기도 값이 false면 순서를 모름
                        check = false;
                        break;
                    }
                    // 값 true면 j번째가 i번째보다 크다는 뜻이니까 순서 알 수 있음
                }
            }
            if(check) RESULT += 1;
        }
    }
}