import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int[] Aarr = new int[K+1];
        int[] Barr = new int[K+1];

        Aarr[0] = 1;
        Barr[0] = 0;
        Aarr[1] = 0;
        Barr[1] = 1;

        for(int i=2; i<=K; i++) {
            Aarr[i] = Aarr[i-1] + Aarr[i-2];
            Barr[i] = Barr[i-1] + Barr[i-2];
        }

        System.out.println(Aarr[K] + " " + Barr[K]);
    }
}