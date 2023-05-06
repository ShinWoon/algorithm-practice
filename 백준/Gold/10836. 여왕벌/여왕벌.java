import java.io.*;

public class Main {
    static int N, M;
    static int[] growthAmount;
    static long[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        N = Integer.parseInt(tmp[1]);

        growthAmount = new int[2 * M - 1];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int idx = 0;
            for(int j=0; j<3; j++) {
            int repeat = Integer.parseInt(tmp[j]);
            if(repeat == 0) continue;
                for(int r=0; r<repeat; r++) growthAmount[idx++] += j;
            }
        }

        solve();
        printArr();
    }

    private static void solve() {
            // 먼저 제일 왼쪽, 제일 위에 있는 애들 계산해주기
        map = new long[M][M];
            int yIdx = M - 1, xIdx = 1;
            for (int num : growthAmount) {
                if (yIdx >= 0) {
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
                long numChosen = Math.max(map[j-1][i-1], map[j-1][i]); // 왼쪽 위, 위 비교
                numChosen = Math.max(map[j][i-1], numChosen); // 왼쪽 비교
                map[j][i] = numChosen;
            }
        }
    }

    private static void printArr() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<M; i++) {
             for(int j=0; j<M; j++) {
                 bw.append((map[i][j] + 1) + " ");  // 애벌레 1부터 시작이었으니까
             }
            bw.append("\n");
        }
        bw.flush();
    }
}