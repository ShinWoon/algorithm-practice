import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, maxSum;
    private static int[][] diceInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        diceInfo = new int[N][6];
        for(int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            for(int j=0; j<6; j++) diceInfo[i][j] = Integer.parseInt(tmp[j]);
        }

        maxSum = 0;
        // 1번 주사위 돌려보면서 최댓값 구하자
        for(int i=0; i<6; i++) {
            solve(i);
//            System.out.println("이번: " + diceInfo[0][i]);
//            System.out.println("----------");
        }

        System.out.println(maxSum);
    }

    private static void solve(int idx) {
        int top = diceInfo[0][idx];
        int numSum = findMaxNum(idx, 0);

        // 이번에 정해진 1번 주사위 윗면을 base로 다른 주사위의 최댓값을 구하자
        for(int i=1; i<N; i++) {
             for(int j=0; j<6; j++) {
                 if(diceInfo[i][j] == top) {
                     numSum += findMaxNum(j, i);
                     top = diceInfo[i][setTop(j)];
                     break;
                 }
             }
        }
        maxSum = Math.max(maxSum, numSum);
    }

    private static int findMaxNum(int idx, int row) {
        int maxNumber = 0;
        if(idx == 0 || idx == 5){
            // 양 끝인 경우
            for(int i=1; i<=4; i++) maxNumber = Math.max(maxNumber, diceInfo[row][i]);
        } else if (idx == 1 || idx == 3) {
            for(int i=0; i<6; i++) {
                if(i == 1 || i == 3) continue;
                maxNumber = Math.max(maxNumber, diceInfo[row][i]);
            }
        } else if(idx == 2 || idx == 4){
            for(int i=0; i<6; i++) {
                if(i == 2 || i == 4) continue;
                maxNumber = Math.max(maxNumber, diceInfo[row][i]);
            }
        }
//        System.out.println(maxNumber);
        return maxNumber;
    }

    private static int setTop(int idx) {
        if(idx == 0) return 5;
        else if (idx == 1) return 3;
        else if (idx == 2) return 4;
        else if (idx == 3) return 1;
        else if (idx == 4) return 2;
        else return 0;
    }
}