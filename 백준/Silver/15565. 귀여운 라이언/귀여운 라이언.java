import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) dolls[i] = Integer.parseInt(st.nextToken());


        int start = 0;
        int end = 0;
        int ryanCnt = 0;
        if(dolls[start] == 1) ryanCnt++;
        int result = Integer.MAX_VALUE;
        while(end < N) {
            try{
                if(ryanCnt >= K) {  // 라이언 인형 수가 K 이상이면
                    result = Math.min(result, end - start + 1);
                    if(dolls[start] == 1) ryanCnt--;
                    start++;
                } else {
                    end++;
                    if(dolls[end] == 1) ryanCnt++;
                }
            } catch (Exception e) {
                break;
            }
        }
        if(result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }
}