import java.util.Scanner;

public class Solution {
    static int N, M, C, resultAmount;
    static int[][] map;
    static int[] honeyPot;
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();

            map = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            solve();
            System.out.println("#" + test_case + " " + resultAmount);
        }
    }

    private static void solve() {
        honeyPot = new int[N*N];
        resultAmount = 0;

        int idx = 0;
        int[] selected = new int[M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<=N-M; j++) {
                for(int k=0; k<M; k++) selected[k] = map[i][j+k];
                int reulst = setHoneyPot(selected);
                honeyPot[idx++] = reulst;
            }
            for(int j=0; j<M-1; j++) honeyPot[idx++] = 0;
        }

        getMaxAmount();
    }

    private static int setHoneyPot(int[] selected) {
        int maxSelected = 0;

        // 부분조합
        for(int i=1; i<(1<<M); i++) {
            int sum = 0;
            int result = 0;
            for(int j=0; j<M; j++) {
                if((i&(1<<j))!= 0) {
                    sum += selected[j];
                    result += selected[j] * selected[j];
                }
            }
            if(sum <= C) {
                maxSelected = Math.max(maxSelected, result);
            }
        }
        return maxSelected;
    }

    // 저장되어 있는 값들을 i는 i+M부터 쭉 돌면서 최댓값 찾기
    private static void getMaxAmount() {
        int maxAmount = 0;
        for(int i=0; i<honeyPot.length-M; i++) {
            int num1 = honeyPot[i];
//            System.out.println(num1);
            for(int j=i+M; j<honeyPot.length; j++) {
                int num2 = honeyPot[j];
                maxAmount = Math.max(maxAmount, num1 + num2);
            }
        }
        resultAmount = maxAmount;
    }
}