import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static long RESULT;
    private static int[][] map, sumMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String inputStr = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(inputStr.charAt(j - 1)));
            }
        }

        sumMap = new int[N + 1][M + 1]; // 누적합 배열
        setSumMap();

        RESULT = 0;
        // 직사각형 3개로 나누기
        solution();
        System.out.println(RESULT);
    }

    private static void solution() {
        long boxOne = 0;
        long boxTwo = 0;
        long boxThree = 0;

        // 긴 세로 3개
        for (int col = 1; col <= M - 2; col++) {
            for (int sndCol = col + 1; sndCol <= M - 1; sndCol++) {
                for (int thrCol = sndCol + 1; thrCol <= M; thrCol++) {
                    boxOne = sumMap[N][col];
                    boxTwo = sumMap[N][sndCol] - sumMap[N][col];
                    boxThree = sumMap[N][thrCol] - sumMap[N][sndCol];
                    RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
                }
            }
        }

        // 긴 가로 3개
        for (int row = 1; row <= N - 2; row++) {
            for (int sndRow = row + 1; sndRow <= N - 1; sndRow++) {
                for (int trdRow = sndRow + 1; trdRow <= N; trdRow++) {
                    boxOne = sumMap[row][M];
                    boxTwo = sumMap[sndRow][M] - sumMap[row][M];
                    boxThree = sumMap[trdRow][M] - sumMap[sndRow][M];
                    RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
                }
            }
        }

        // 긴 가로 하단에 1개
        for (int row = N; row > 1; row--) {
            for (int col = 1; col <= M - 1; col++) {
                boxOne = sumMap[N][M] - sumMap[row - 1][M];
                boxTwo = sumMap[row - 1][col];
                boxThree = sumMap[row - 1][M] - sumMap[row - 1][col];
                RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
            }
        }

        // 긴 가로 상단에 1개
        for (int row = 1; row < N; row++) {
            for (int col = 1; col <= M - 1; col++) {
                boxOne = sumMap[row][M];
                boxTwo = sumMap[N][col] - sumMap[row][col];
                boxThree = sumMap[N][M] - sumMap[N][col] - sumMap[row][M] + sumMap[row][col];
                RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
            }
        }

        // 긴 세로 왼쪽에 1개
        for(int col=1; col<=M-1; col++) {
            for(int row=1; row<=N-1; row++) {
                boxOne = sumMap[N][col];
                boxTwo = sumMap[row][M] - sumMap[row][col];
                boxThree = sumMap[N][M] - sumMap[row][M] - sumMap[N][col] + sumMap[row][col];
                RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
            }
        }

        // 긴 세로 오른쪽에 1개
        for (int col=M; col>1; col--) {
            for(int row=1; row<=N-1; row++) {
                boxOne = sumMap[N][M] - sumMap[N][col-1];
                boxTwo = sumMap[row][col-1];
                boxThree = sumMap[N][col-1] - sumMap[row][col-1];
                RESULT = Math.max(RESULT, boxOne * boxTwo * boxThree);
            }
        }

    }

    private static void setSumMap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sumMap[i][j] = sumMap[i - 1][j] + sumMap[i][j - 1] - sumMap[i - 1][j - 1] + map[i][j];  // 직사각형 2개 더하고 중복되는 부분 한번 빼주고, 현재 Map에 있는 값을 채워넣고
            }
        }
    }
}