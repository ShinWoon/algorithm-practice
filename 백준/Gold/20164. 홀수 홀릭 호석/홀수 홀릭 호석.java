import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, minNum, maxNum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        minNum = Integer.MAX_VALUE;
        maxNum = Integer.MIN_VALUE;

        dfs(N, 0);
        System.out.println(minNum + " " + maxNum);
    }
    private static void dfs(int num, int cnt) {
        cnt += countOdd(num);
        if(num < 10) {
            minNum = Math.min(minNum, cnt);
            maxNum = Math.max(maxNum, cnt);
            return;
        }
        String strNum = String.valueOf(num);
        int[] arr;
        if(num < 100) {
            // 두 자리 수
            arr = new int[3];
            arr[0] = strNum.charAt(0) - '0';
            arr[1] = strNum.charAt(1) - '0';
            arr[2] = arr[0] + arr[1];
            dfs(arr[2], cnt);
        } else {
            // 세 자리 수 이상
            arr = new int[4];
            for(int i=1; i<=strNum.length()-1; i++) {
                arr[0] = Integer.parseInt(strNum.substring(0, i));
                for(int j=i+1; j<strNum.length(); j++) {
                    arr[1] = Integer.parseInt(strNum.substring(i, j));
                    arr[2] = Integer.parseInt(strNum.substring(j));
                    arr[3] = arr[0] + arr[1] + arr[2];
                    dfs(arr[3], cnt);
                }
            }
        }
    }
    private static int countOdd(int sumNum) {
        int oddCnt = 0;
        while(sumNum > 0) {
            int num = sumNum % 10;
            if(num % 2 == 1) oddCnt++;
            sumNum /= 10;
        }
        return oddCnt;
    }
}
