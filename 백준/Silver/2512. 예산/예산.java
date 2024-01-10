import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, RESULT;
    private static long M;
    private static int[] budget;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        RESULT = 0;
        st = new StringTokenizer(br.readLine());
        budget = new int[N];
        int total = 0;  // 현재 예산 값 총액
        int maxNum = 0; // 예산 값 중 가장 큰 수
        for(int i=0; i<N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            total += budget[i];
            maxNum = Math.max(maxNum, budget[i]);
        }
        M = Long.parseLong(br.readLine());

        if(total <= M) RESULT = maxNum; // 애초에 다 더해도 M 값보다 작으면 예산 최댓값으로 출력
        else solve();   // 예산 값 정하기
        System.out.println(RESULT);
    }
    private static void solve() {
        int start = 0;
        int end = 100000;
        int middle;

        while (start<=end) {
            middle = (start+end)/2;
            long cnt = 0;
            for(int i=0; i<N; i++) {
                // 상한액 이하면 요청 금액, 상한액 이상이면 상한액으로 계산
                if(budget[i] <= middle) cnt += budget[i];
                else cnt += middle;
            }
            if(cnt <= M) {  // 총 값이 M보다 작으면 상한액 높이기
                start = middle+1;
            } else {    // 총 값이 크면 상한액 줄이기
                end = middle-1;
            }
        }
        RESULT = end;
    }
}