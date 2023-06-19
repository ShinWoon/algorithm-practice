import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M, numSum, resultMaxSum;
    private static int[][] map, xy;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        solve();
        System.out.println(resultMaxSum);
    }

    private static void solve() {
        resultMaxSum = 0;   // 합의 최댓값 저장 변수
        tetromino1();
        tetromino2();
        tetromino3();
        tetromino4();
        tetromino5();
    }

    /**
     * ㅁㅁ
     * ㅁㅁ
     */
    private static void tetromino1() {
        // 회전 필요 없음
        int[] dx = {0, 1, 0, 1};
        int[] dy = {0, 0, 1, 1};
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                numSum = 0;
                for (int d = 0; d < 4; d++) {
                    numSum += map[i + dy[d]][j + dx[d]];
                }
                resultMaxSum = Math.max(resultMaxSum, numSum);
            }
        }
    }

    /**
     * ㅁㅁㅁㅁ
     */
    private static void tetromino2() {
        // 방법 2개
        int[][] dx = {{0, 1, 2, 3}, {0, 0, 0, 0}};
        int[][] dy = {{0, 0, 0, 0}, {0, 1, 2, 3}};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 3; j++) {
                numSum = 0;
                for (int d = 0; d < 4; d++) {
                    numSum += map[i + dy[0][d]][j + dx[0][d]];
                }
                resultMaxSum = Math.max(resultMaxSum, numSum);
            }
        }

        for (int i = 0; i < N - 3; i++) {
            for (int j = 0; j < M; j++) {
                numSum = 0;
                for (int d = 0; d < 4; d++) {
                    numSum += map[i + dy[1][d]][j + dx[1][d]];
                }
                resultMaxSum = Math.max(resultMaxSum, numSum);
            }
        }
    }

    /**
     * ㅁ
     * ㅁ
     * ㅁㅁ
     */
    private static void tetromino3() {  // 회전 + 대칭 고려
        int[][] dx = {{0, 0, 0, 1}, {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}, {0, 1, 2, 2}};
        int[][] dy = {{0, 1, 2, 2}, {0, 0, 0, -1}, {0, 0, 1, 2}, {0, -1, -1, -1}, {0, 0, -1, -2}, {0, 1, 1, 1}, {0, -1, -2, -2}, {0, 0, 0, 1}};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int turn = 0; turn < 8; turn++) {
                    xy = new int[4][2];
                    for (int d = 0; d < 4; d++) {
                        xy[d][0] = i + dy[turn][d];
                        xy[d][1] = j + dx[turn][d];
                    }
                    if (check()) {
                        numSum = 0;
                        for (int d = 0; d < 4; d++) {
                            numSum += map[xy[d][0]][xy[d][1]];
                        }
                        resultMaxSum = Math.max(resultMaxSum, numSum);
                    }
                }
            }
        }
    }

    /**
     * ㅁ
     * ㅁㅁ
     *  ㅁ
     */
    private static void tetromino4() {  // 회전 + 대칭 고려
        int[][] dx = {{0, 0, 1, 1}, {0, 1, 1, 2}, {0, 0, 1, 1}, {0, 1, 1, 2}};
        int[][] dy = {{0, 1, 1, 2}, {0, 0, -1, -1}, {0, -1, -1, -2}, {0, 0, 1, 1}};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int turn = 0; turn < 4; turn++) {
                    xy = new int[4][2];
                    for (int d = 0; d < 4; d++) {
                        xy[d][0] = i + dy[turn][d];
                        xy[d][1] = j + dx[turn][d];
                    }
                    if (check()) {
                        numSum = 0;
                        for (int d = 0; d < 4; d++) {
                            numSum += map[xy[d][0]][xy[d][1]];
                        }
                        resultMaxSum = Math.max(resultMaxSum, numSum);
                    }
                }
            }
        }
    }

    /**
     * ㅁㅁㅁ
     *  ㅁ
     */
    private static void tetromino5() {
        int[][] dx = {{0, 1, 1, 2}, {0, 1, 1, 1}, {0, 1, 1, 2}, {0, 0, 1, 0}};
        int[][] dy = {{0, 0, 1, 0}, {0, 0, -1, 1}, {0, 0, -1, 0}, {0, 1, 1, 2}};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int turn = 0; turn < 4; turn++) {
                    xy = new int[4][2];
                    for (int d = 0; d < 4; d++) {
                        xy[d][0] = i + dy[turn][d];
                        xy[d][1] = j + dx[turn][d];
                    }
                    if (check()) {
                        numSum = 0;
                        for (int d = 0; d < 4; d++) {
                            numSum += map[xy[d][0]][xy[d][1]];
                        }
                        resultMaxSum = Math.max(resultMaxSum, numSum);
                    }
                }
            }
        }
    }

    private static boolean check() {
        boolean checkPossible = true;
        for (int i = 0; i < 4; i++) {
            if (xy[i][0] < 0 || xy[i][0] >= N || xy[i][1] < 0 || xy[i][1] >= M) {
                checkPossible = false;
                break;
            }
        }
        return checkPossible;
    }
}