import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] immigrationCheckpoint;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        immigrationCheckpoint = new int[N];
        long maxTime = Long.MIN_VALUE;
        for (int i=0; i<N; i++) {
            immigrationCheckpoint[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, immigrationCheckpoint[i]);
        }

        Arrays.sort(immigrationCheckpoint);

        long start = 0;
        long end = maxTime * M;  // 제일 긴 시간 * 사람 수
        long result = end;
        while (start <= end) {
            long middle = (start + end)/2;
            if(checkPeople(middle) < M) {   // M보다 적으면 시간을 늘리자
                start = middle + 1;
            } else {    // 시간을 줄여보자
                result = Math.min(result, middle);
                end = middle - 1;
            }
        }
        System.out.println(result);
    }
    private static long checkPeople(long time) {
        long cnt = 0;
        // 각 입국 심사대에서 이 시간 내에 심사할 수 있는 사람 수
        for(int i=0; i<N; i++) {
            cnt += time/immigrationCheckpoint[i];
            if(cnt >= M) return cnt;
        }
        return cnt;
    }
}