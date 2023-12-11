import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, RESULT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        RESULT = 0;
        int[][] map = new int[N][N];
        for (int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        solve(map, 0);
        System.out.println(RESULT); // 가장 큰 블록
    }

    private static void solve(int[][] map, int time) {
        if(time == 5) { // 최대 5번까지
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    RESULT = Math.max(map[i][j], RESULT);
                }
            }
            return;
        }
        if(checkIfDone(map)) { // 블록 하나만 남았으면
            return;
        }

        for(int i=0; i<4; i++) {
            int[][] movedMap = move(map, i);
//           printMap(movedMap);
            solve(movedMap, time+1);
        }
    }

    private static int[][] move(int[][] map, int dir) {
        int[][] tmpMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }
        if(dir == 0) {  // 상
            for(int i=0; i<N; i++) {    // 열
                int idx = 0;
                int tmpNum = 0;
                for(int j=0; j<N; j++) { // 행
                    if(tmpMap[j][i] != 0) {
                        if(tmpMap[j][i] == tmpNum) {
                            tmpMap[idx-1][i] = tmpNum * 2;
                            tmpNum = 0;
                            tmpMap[j][i] = 0;
                        } else {
                            tmpNum = tmpMap[j][i];
                            tmpMap[j][i] = 0;
                            tmpMap[idx][i] = tmpNum;
                            idx++;
                        }
                    }
                }
            }
        } else if(dir == 1) {   // 하
            for(int i=0; i<N; i++) {    // 열
                int idx = N-1;
                int tmpNum = 0;
                for(int j=N-1; j>=0; j--) {  // 행
                    if(tmpMap[j][i] != 0) {
                        if(tmpMap[j][i] == tmpNum) {
                            tmpMap[idx+1][i] = tmpNum * 2;
                            tmpNum = 0;
                            tmpMap[j][i] = 0;
                        } else {
                            tmpNum = tmpMap[j][i];
                            tmpMap[j][i] = 0;
                            tmpMap[idx][i] = tmpNum;
                            idx--;
                        }
                    }
                }
            }

        } else if(dir == 2) {   // 좌
            for(int i=0; i<N; i++) {    // 행
                int idx = 0;
                int tmpNum = 0;
                for(int j=0; j<N; j++) {  // 열
                    if(tmpMap[i][j] != 0) {
                        if(tmpMap[i][j] == tmpNum) {
                            tmpMap[i][idx-1] = tmpNum * 2;
                            tmpNum = 0;
                            tmpMap[i][j] = 0;
                        } else {
                            tmpNum = tmpMap[i][j];
                            tmpMap[i][j] = 0;
                            tmpMap[i][idx] = tmpNum;
                            idx++;
                        }
                    }
                }
            }

        } else {    // 우
            for(int i=0; i<N; i++) {    // 행
                int idx = N-1;
                int tmpNum = 0;
                for(int j=N-1; j>=0; j--) {  // 열
                    if(tmpMap[i][j] != 0) {
                        if(tmpMap[i][j] == tmpNum) {
                            tmpMap[i][idx+1] = tmpNum * 2;
                            tmpNum = 0;
                            tmpMap[i][j] = 0;
                        } else {
                            tmpNum = tmpMap[i][j];
                            tmpMap[i][j] = 0;
                            tmpMap[i][idx] = tmpNum;
                            idx--;
                        }
                    }
                }
            }

        }
        return tmpMap;
    }

    private static boolean checkIfDone(int[][] map) {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] != 0) {
                    cnt++;
                    RESULT = Math.max(map[i][j], RESULT);
                }
            }
        }
        if(cnt > 1) return false;
        return true;
    }

    private static void printMap(int[][] tmpMap) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(tmpMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}