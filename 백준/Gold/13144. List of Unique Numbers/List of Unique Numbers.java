import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static long result;
    private static int[] numArr;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<tmp.length; i++) {
            numArr[i] = Integer.parseInt(tmp[i]);
        }

        result = 0;
        solve();
        System.out.println(result);
    }

    private static void solve() {
        visited = new boolean[100_001];

        int end = 0;
        for(int start=0; start<N; start++) {
            while (end < N) {
                if(visited[numArr[end]]) {
                    break;
                }
                visited[numArr[end]] = true;
                end++;
            }
            result += (end-start);
//            System.out.println(result);
            visited[numArr[start]] = false;
        }

    }
}