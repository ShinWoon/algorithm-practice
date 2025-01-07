import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A, B, C, M;
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int work = 0;
        int tired = 0;
        int time = 0;
        while(time < 24) {
            if(A > M) break;
            if(tired + A <= M) {
                work += B;
                tired += A;
            } else {
                tired -= C;
                if(tired < 0) tired = 0;
            }
            time++;
        }
        System.out.println(work);
    }
}