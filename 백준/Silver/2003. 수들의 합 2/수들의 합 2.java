import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        int cnt = 0;
        int sum = A[start];
        while(end < N) {
            try {
                if(sum == M) {  // 구간 증가, 앞 뒤 다 뒤로 가기
                    cnt++;
                    sum -= A[start];
                    start += 1;
                    end += 1;
                    sum += A[end];
                } else if (sum < M) {   // 값이 작으면 뒤로 가서 값 증가 시키기
                    end += 1;
                    sum += A[end];
                } else {    // 값이 크면 앞을 이동
                    if(start == end) {
                        end += 1;
                        sum += A[end];
                    } else {
                        sum -= A[start];
                        start += 1;
                    }
                }
            } catch (Exception e) { // 구간 벗어나면 끊어버리기
                break;
            }
        }
        System.out.println(cnt);
    }
}