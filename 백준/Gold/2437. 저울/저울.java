import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static long resultWeight;
    static int[] weightArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] tmp = br.readLine().split(" ");
        weightArr = new int[N];
        for(int i=0; i<N; i++) weightArr[i] = Integer.parseInt(tmp[i]);

        solve();
        System.out.println(resultWeight);
    }

    private static void solve() {
        resultWeight = 0;

        Arrays.sort(weightArr);
        long sum = 1;
        for(int i=0; i<N; i++) {
            if(sum >= weightArr[i]) {   // 만약 sum 값이 지금 추 값보다 크거나 같으면 이전에 모든 무게 경우를 만들 수 있음.
                sum += weightArr[i];
            } else {    // sum이 더 작으면 이번 추 이전에 만들 수 있는 모든 무게 경우 못 만듦
                break;
            }
//            System.out.println(sum);
        }
        resultWeight = sum;   // 다음 무게부터 불가능
    }
}