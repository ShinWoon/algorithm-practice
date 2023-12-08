import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] lenArr = new int[N+1];
        lenArr[1] = 1;

        for(int i=2; i<=N; i++) {
            lenArr[i] = lenArr[i-1] + lenArr[i-2];
        }
        int result = 2 * ( 2 * lenArr[N] + lenArr[N-1]); // 이번 변 길이 * 2 + (이번 변 길이 + 이전 변 길이) * 2
        System.out.println(result);
    }
}