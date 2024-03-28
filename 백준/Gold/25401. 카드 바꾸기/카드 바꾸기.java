import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, RESULT;
    private static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        RESULT = Integer.MAX_VALUE;
        for(int i=0; i<N-1; i++) {
            int diff = arr[i+1] - arr[i];

            int cnt = 0;
            for(int j=0; j<N; j++) {
                int num = 0;    // 이번에 되어야할 숫자
                if(j < i) { // 감소나 증가가 앞에 숫자는 반대로 가야하니까 -1 곱하기
                     num = -1 * diff * (i - j) + arr[i];
                } else {    // 뒤로 이어지는 건 그냥 하면 됨
                    num = diff * (j -i) + arr[i];
                }
                // 일치 안하면 카드 수 증가
                if(num != arr[j]) cnt += 1;
            }
            RESULT = Math.min(cnt, RESULT);
        }
        System.out.println(RESULT);
    }
}