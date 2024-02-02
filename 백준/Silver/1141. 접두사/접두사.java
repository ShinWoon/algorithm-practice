import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];
        for (int i=0; i<N; i++) {
            strArr[i] = br.readLine();
        }
        Arrays.sort(strArr);
        int result = N;
        for(int i=0; i<N-1; i++) {
            if(strArr[i+1].startsWith(strArr[i])){
                result--;
            }
        }
        System.out.println(result);
    }
}