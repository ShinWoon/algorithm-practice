import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, L;
    private static int[] restArea;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        restArea = new int[N+2];
        restArea[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            restArea[i] = Integer.parseInt(st.nextToken());
        }
        restArea[N+1] = L;

        Arrays.sort(restArea);  // 휴게소 거리로 일단 정렬

        int start = 1;
        int end = L-1;
        int result = Integer.MAX_VALUE;
        while (start <= end) {
            int middle = (start+end)/2;
            if(buildRestArea(middle) <= M) {
                end = middle - 1;
                result = Math.min(result, middle);
            } else {
                start = middle + 1;
            }
        }
        System.out.println(end+1);
    }

    private static int buildRestArea(int length) {
        int cnt = 0;
        for(int i=0; i<N+1; i++) {
            int front = restArea[i] + 1;    // 휴게소 위치에는 건설할 수 없기 때문에
            int back = restArea[i+1] - 1;
            int distance = back - front + 1;
            cnt += distance/length;
        }
        return cnt;
    }
}