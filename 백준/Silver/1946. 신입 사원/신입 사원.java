import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static int N, result;
    private static int[][] info;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0; test_case<T; test_case++) {
            N = Integer.parseInt(br.readLine());
            info = new int[N][2];
            String[] tmp;
            for(int i=0; i<N; i++) {
                tmp = br.readLine().split(" ");
                info[i][0] = Integer.parseInt(tmp[0]);
                info[i][1] = Integer.parseInt(tmp[1]);
            }

            result = 1;
            solve();
            System.out.println(result);
        }
    }

    private static void solve() {
        Arrays.sort(info, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int least = info[0][1];
        for(int i=1; i<N; i++) {
            if(least >= info[i][1]) {
                least = info[i][1];
                result++;
            }
        }
    }
}