import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M, K, A;
    private static int[][] map, aMap, tmpMap, functionArr;
    private static int[] indexArr;
    private static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(tmp[2]);

        map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(tmp[j-1]);
            }
        }

        functionArr = new int[K][3];
        for (int i = 0; i < K; i++) {
            tmp = br.readLine().split(" ");
            int r = Integer.parseInt(tmp[0]);
            int c = Integer.parseInt(tmp[1]);
            int s = Integer.parseInt(tmp[2]);
            functionArr[i][0] = r;
            functionArr[i][1] = c;
            functionArr[i][2] = s;
        }

        A = Integer.MAX_VALUE;
        indexArr = new int[K];  // 연산 순서 순열
        visited = new boolean[K];

        permutation(0);
        System.out.println(A);
    }

    // 연산 순서 순열
    private static void permutation(int level) {
        if (level == K) {
            // 순열 만들어졌음
            aMap = copyMap();
            // 배열 돌리기
            for (int i = 0; i < K; i++) {
                aMap = solve(i);
            }
            // 배열 다 돌렸음
            addRow();
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            indexArr[level] = i;
            permutation(level + 1);
            visited[i] = false;
        }
    }

    // 연산 수행하기
    private static int[][] solve(int turn) {
        int r = functionArr[indexArr[turn]][0];
        int c = functionArr[indexArr[turn]][1];
        int s = functionArr[indexArr[turn]][2];
        int y1 = r - s;
        int x1 = c - s;
        int y2 = r + s;
        int x2 = c + s;

        tmpMap = new int[N+1][M+1];
        // 중앙에 있는 곳까지 도달할 때까지 값들을 시계 방향으로 돌리기
        while (y1 < y2 && x1 < x2) {

            // tmpMap에 aMap 값 복사
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    tmpMap[i][j] = aMap[i][j];
                }
            }

            // 값 시계 방향으로 돌리기
            // 윗변
            for(int x=x1+1, y=y1; x<=x2; x++) {
                tmpMap[y][x] = aMap[y][x-1];
            }
            // 오른쪽변
            for(int x=x2, y=y1+1; y<=y2; y++) {
                tmpMap[y][x] = aMap[y-1][x];
            }
            // 아랫변
            for(int x=x2-1, y=y2; x>=x1; x--) {
                tmpMap[y][x] = aMap[y][x+1];
            }
            // 왼쪽변
            for(int x=x1, y=y2-1; y>=y1; y--) {
                tmpMap[y][x] = aMap[y+1][x];
            }

            // tmpMap 값을 aMap에 복사
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    aMap[i][j] = tmpMap[i][j];
                }
            }

            // x1, y1, x2, y2 값 변경
            x1 += 1;
            y1 += 1;
            x2 -= 1;
            y2 -= 1;
        }

        return aMap;
    }

    // 각 행 값을 더하고 최솟값 갱신
    private static void addRow() {
        int sum;
        for (int i = 1; i <= N; i++) {
            sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += aMap[i][j];
            }
            A = Math.min(A, sum);
        }
    }

    private static int[][] copyMap() {
        aMap = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                aMap[i][j] = map[i][j];
            }
        }
        return aMap;
    }
}