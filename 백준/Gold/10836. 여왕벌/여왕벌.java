import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int[][] map, growthAmount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        N = Integer.parseInt(tmp[1]);

        growthAmount = new int[N][2 * M - 1];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int idx = 0;
            for(int j=0; j<3; j++) {
            int repeat = Integer.parseInt(tmp[j]);
                for(int r=0; r<repeat; r++) growthAmount[i][idx++] = j;
            }
        }

        map = new int[M][M];
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) map[i][j] = 1;
        }

        solve();
        printArr();
    }

    private static void solve() {
        for(int day=0; day<N; day++) {
            // 먼저 제일 왼쪽, 제일 위에 있는 애들 계산해주기
            int yIdx = M-1, xIdx = 1;
            for(int num : growthAmount[day]) {
                if(yIdx>=0) {
                    map[yIdx--][0] += num;
                } else {
                    map[0][xIdx++] += num;
                }
            }
            // 나머지 애들 계산해주기
            for(int i=1; i<M ; i++) {
                // i = 열
                for(int j=1; j<M; j++) {
                    // j = 행
                    int numChosen = Math.max(map[i-1][j-1], map[i-1][j]); // 왼쪽 위, 위 비교
                    numChosen = Math.max(map[i][j-1], numChosen); // 왼쪽 비교
                    map[i][j] = numChosen;
                }
            }
        }

    }

    private static void printArr() {
        for(int i=0; i<M; i++) {
             for(int j=0; j<M; j++) {
                 System.out.print(map[i][j] + " ");
             }
            System.out.println();
        }
    }
}