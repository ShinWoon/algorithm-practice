import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, r, c, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int len = (int)Math.pow(2, N);

        answer = 0;
        solve(len, 0, 0, 0);
        System.out.println(answer);
    }

    private static void solve(int len, int y, int x, int cnt) {
        if(len == 1) {
            answer = cnt;
            return;
        }
        
        len /= 2;
        if(r < x+len && c < y+len) {
            // 제1사분면
            solve(len, y, x, cnt + (len * len * 0));
        } else if(r < x+len && c >= y+len) {
            // 제2사분면
            solve(len, y + len, x, cnt + (len * len * 1));
        } else if(c < y+len) {
            // 제3사분면
            solve(len, y, x + len, cnt + (len * len * 2));
        } else {
            // 제4사분면
            solve(len, y + len, x + len, cnt + (len * len * 3));
        }
        return;
    }
}
