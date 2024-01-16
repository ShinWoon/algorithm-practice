import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cnt = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) cnt[i] = Integer.parseInt(st.nextToken());

        int longestLine = 0;    // 가장 길다고 생각하는 애
        int line = 0;   // 1을 기준으로 분기 태운 길이
        for(int i=0; i<N; i++) {
            longestLine++;  // 길다고 생각하는 애는 계속 증가 시켜줌 1자로
            if(cnt[i] == 1) {
                longestLine = Math.max(longestLine, line+1);
                line = 0;   // 이제 이후로 새롭게 분기를 태울 거니까
            } else {
                line += 2;  // 분기를 태운 상태라고 간주하고 2씩 증가해주는 것
            }
        }
        System.out.println(Math.max(longestLine, line));    // 가장 길다고 생각한 1자 길이랑 분기 태운 길이랑 비교해서 긴거 선택
    }
}