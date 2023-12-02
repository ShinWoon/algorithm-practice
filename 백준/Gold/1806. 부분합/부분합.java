import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static int N, S;
    public static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        S = Integer.parseInt(tmp[1]);

        num = new int[N];
        tmp = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(tmp[i]);

        int result = solve();
        
        // 최소길이 값이 변한 경우
        if(result != 100001)
            System.out.println(result);
        else
            System.out.println(0);
    }

    public static int solve() { //투포인터를 활용하여 부분합 구함
        int minLen = 100001, sum = 0, end = 0;

        for (int start = 0; start < N; start++) {
            while (sum < S && end < N) {    // 부분합의 값이 목표하는 값보다 작거나 end 포인터 값이 N보다 작은 경우에만
                sum += num[end++];
            }
            if (sum >= S) { // 만약에 합이 목표값보다 크거나 같으면 두 원소 위치 길이 저장
                minLen = Math.min(minLen, end - start);
            }
            sum -= num[start];  // start 위치 앞으로 땡기기 위래 현재 start 위치에 있는 값 빼주기
        }
        return minLen;
    }
}