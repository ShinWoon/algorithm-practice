import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] childrenArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        childrenArr = new int[N];

        for(int i=0; i<N; i++) {
            childrenArr[i] = Integer.parseInt(br.readLine());
        }

        int result = solve();
        System.out.println(result);
    }
    public static int solve() {
        int[] length = new int[N];

        for(int k=0; k<N; k++) {
            length[k] = 1;
            for(int i=0; i<k; i++) {
                if(childrenArr[i] < childrenArr[k]) {
                    length[k] = Math.max(length[k], length[i] + 1);
                }
            }
        }
        int maxLen = 0;
        for(int i=0; i<N; i++) maxLen = Math.max(maxLen, length[i]);
        return N-maxLen;
    }

}