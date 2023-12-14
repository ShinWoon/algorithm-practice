import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(tmp[i]);

        Arrays.sort(arr);
        int result = 0;
        int sum = 0;
        for(int i=0; i<N; i++) {
            sum += arr[i];
            result += sum;
        }
        System.out.println(result);
    }
}