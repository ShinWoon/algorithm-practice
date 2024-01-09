import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int K, N;
    private static long RESULT;
    private static long[] lanArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        RESULT = 0;

        lanArr = new long[K];
        for(int i=0; i<K; i++) {
            lanArr[i] = Integer.parseInt(br.readLine());
        }

        solve();
        System.out.println(RESULT);
    }

    private static void solve() {
        long start = 1;
        long end = Long.MAX_VALUE-1;
        long middle = 0;
        while(start < end) {
            middle = (start + end)/2;
//            System.out.println(middle);
            int cnt = 0;
            for(int i=0; i<K; i++) {
                cnt += lanArr[i]/middle;
            }
            if(cnt < N) {
                end = middle;
            } else{ // 그래도 최대한 긴게 좋으니까
                start = middle + 1;
            }
        }
        RESULT = end - 1;
    }
}