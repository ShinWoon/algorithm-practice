import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static long[] minNum;
    private static String[] maxNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        minNum = new long[101];
        maxNum = new String[101];

        setMinNum();
        setMaxNum();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            sb.append(minNum[N] + " " + maxNum[N] + "\n");
        }
        System.out.println(sb);
    }

    private static void setMinNum() {
//        System.out.println("here");
        for (int i = 2; i < 101; i++) minNum[i] = Long.MAX_VALUE;

        minNum[2] = 1;
        minNum[3] = 7;
        minNum[4] = 4;
        minNum[5] = 2;
        minNum[6] = 6;
        minNum[7] = 8;
        minNum[8] = 10;

        for (int i = 9; i <= 100; i++) {    // j가 침범하는 범위인지 확인하자!!
            String thisNum = "";
            for (int j = 2; j <= 7; j++) {
                if (j == 6) thisNum = minNum[i - j] + "0";
                else thisNum = String.valueOf(minNum[i - j]) + minNum[j];
                minNum[i] = Math.min(minNum[i], Long.parseLong(thisNum));
            }
        }
    }

    private static void setMaxNum() {
//        System.out.println("here");
        for (int i = 2; i <= 100; i++) {
            String thisNum = "";
            if (i % 2 == 0) {
                // 짝수 -> 1 계속 붙임
                for (int j = 0; j < i / 2; j++) thisNum += '1';
            } else {
                // 홀수 -> 7 붙이고 뒤에 1 계속 붙임
                thisNum += '7';
                for (int j = 0; j < (i - 3) / 2; j++) thisNum += '1';
            }
            maxNum[i] = thisNum;
        }
    }
}