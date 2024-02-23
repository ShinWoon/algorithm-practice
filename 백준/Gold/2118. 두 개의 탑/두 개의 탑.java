import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] distance = new int[N + 1];
        for(int i=1; i<=N; i++) {
            int inputDis = Integer.parseInt(br.readLine());
            distance[i] = distance[i-1] + inputDis;
        }
        int result = 0;
        int clockwiseDis = 0;
        int counterclockwiseDis = 0;
        for(int i=1; i<=N; i++) {
            for(int j=i; j<=N; j++) {
                clockwiseDis = distance[j] - distance[i];
                counterclockwiseDis = distance[N] - clockwiseDis;
                result = Math.max(result, Math.min(clockwiseDis, counterclockwiseDis));
            }
        }
        System.out.println(result);
    }
}