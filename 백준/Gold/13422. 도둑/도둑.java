import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case=0; test_case<T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            houses = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                houses[i] = Integer.parseInt(st.nextToken());
            }
            solve();
        }
    }
    private static void solve() {
        int cnt = 0;
        int moneySum = 0;
        for(int i=0; i<M; i++) {
            moneySum += houses[i];
        }
        // 훔친 돈이 K 미만이면 훔칠 수 있으니까
        if(moneySum < K) cnt += 1;
        if(N == M) {    // 길이 같으면 이것만 처리하고 넘어가자
            System.out.println(cnt);
            return;
        }
        // 마을 돌자
        int start, end;
        for(int i=1; i<N; i++) {
            start = i;
            end = (i + M - 1)%N;
            moneySum -= houses[start-1];  // start 이동 했으니까
            moneySum += houses[end];    // end 이동 했으니까
            if(moneySum < K) cnt += 1;
        }
        System.out.println(cnt);
    }
}