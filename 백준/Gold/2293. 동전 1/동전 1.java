import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, K;
    static int[] moneyType;
    static int[] moneyCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        moneyType = new int[N];
        moneyCnt = new int[K+1];
        for(int i=0; i<N; i++) {
            moneyType[i] = Integer.parseInt(br.readLine());
        }
//        int result = 0;
//        solve();
        int result = solve();
        System.out.println(result);
    }

    public static int solve() {
        // 각 동전별로 저장해서 풀기 -> 뭔가 이상한 곳에서 놓친듯..
        // 전에 했던 계속 누적 시키는 방법
        for(int i=0; i<N; i++) {
            moneyCnt[0] = 1;
            int money = moneyType[i];
            for(int j=0; j<=K; j++) {
                if(money + j > K) break;
                moneyCnt[j+money] += moneyCnt[j];
            }
        }

//        for(int j=1; j<=K; j++) System.out.print(moneyCnt[j] + " ");
//        System.out.println();
//        printArr();

        return moneyCnt[K];
    }

    public static void printArr() {
        for(int i=1; i<=K; i++) System.out.print(i + " ");
        System.out.println();
        for(int i=0; i<N; i++) {
            for(int j=1; j<=K; j++) System.out.print(moneyCnt[j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}