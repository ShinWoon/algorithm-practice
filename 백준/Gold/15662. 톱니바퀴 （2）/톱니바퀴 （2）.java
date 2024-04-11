import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int[][] gear;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        gear = new int[T][8];
        for(int i=0; i<T; i++) {
            String input = br.readLine();
            for (int j=0; j<8; j++) {
                gear[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            solution(num, dir);
//            print();
        }

        // 12 방향 S극 수 구하기
        System.out.println(sCount());
    }
    private static void solution(int gearNum, int dir) {
        int[] resultArr = new int[T];
        resultArr[gearNum] = dir;

        for(int i=gearNum; i>0; i--) {  // 이번 톱니바퀴 왼쪽 애들
            if(resultArr[i] == 0) break;
            if(gear[i][6] != gear[i-1][2]) {
                // 극이 다르니까 반대방향
                resultArr[i-1] = -1 * resultArr[i];
            }
        }
        for(int i=gearNum; i<T-1; i++) { // 이번 톱니바퀴 오른쪽 애들
            if(resultArr[i] == 0) break;
            if(gear[i][2] != gear[i+1][6]) {
                resultArr[i+1] = -1 * resultArr[i];
            }
        }

        // 결과에 따라 톱니바퀴 돌리기
        for(int i=0; i<T; i++) {
            if(resultArr[i] == -1) {
                // 반시계 방향
                moveCounterclockwise(i);
            } else if (resultArr[i] == 1) {
                // 시계 방향
                moveClockwise(i);
            }
        }
    }

    private static void moveClockwise(int idx) {
        int tmp = gear[idx][7];
        for(int i=7; i>0; i--) {
            gear[idx][i] = gear[idx][i-1];
        }
        gear[idx][0] = tmp;
    }
    private static void moveCounterclockwise(int idx) {
        int tmp = gear[idx][0];
        for(int i=0; i<7; i++) {
            gear[idx][i] = gear[idx][i+1];
        }
        gear[idx][7] = tmp;
    }

    private static int sCount() {
        int cnt = 0;
        for(int i=0; i<T; i++) {
            if(gear[i][0] == 1) cnt += 1;
        }
        return cnt;
    }

    private static void print() {
        for(int i=0; i<T; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(gear[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}