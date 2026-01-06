import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int k = 0;
        int s = 3;
        int front = 0;
        int mid = k + 3;

        while (s < N) {
            k++;
            front = s;
            mid = k + 3;
            s = s * 2 + mid;
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            if(k == 0) {
                if(N == 1) sb.append("m");
                else sb.append("o");
                break;
            }
            if (front < N && N <= front + mid) {
                if (N - front == 1) {
                    sb.append("m");
                } else {
                    sb.append("o");
                }
                break;
            } else if (front + mid < N) {
                N -= front + mid;
            }
            k -= 1;
            mid = k + 3;
            front = (front - mid)/2;
        }

        System.out.println(sb);

    }
}