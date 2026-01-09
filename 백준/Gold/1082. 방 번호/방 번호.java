import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] p;
    private static String answer;
    private static String[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        p = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N ; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        dp = new String[M+1];
        Arrays.fill(dp, "");
        answer = "0";

        findMaxNum();
        System.out.println(answer);
    }
    private static void findMaxNum() {
        for(int i=1; i<N; i++) {
            if(p[i] <= M) {
                dp[p[i]] = String.valueOf(i);
                if(answer.compareTo(dp[p[i]]) < 0) answer = dp[p[i]];
            }
        }

        for(int i=1; i<=M; i++) {
            if(dp[i] != "") {
                for(int j=0; j<N; j++) {
                    if(i + p[j] <= M) {
                        if(dp[i + p[j]] == "") {
                            dp[i + p[j]] = dp[i] + j;
                        } else {
                            String newNum = dp[i] + j;
                            String currentNum = dp[i + p[j]];
                            if(newNum.length() > currentNum.length()) {
                                dp[i + p[j]] = newNum;
                            } else if (newNum.length() == currentNum.length()) {
                                if(newNum.compareTo(currentNum) > 0) {
                                    dp[i + p[j]] = newNum;
                                }
                            }
                        }
                        if(answer.length() < dp[i + p[j]].length()) {
                            answer = dp[i + p[j]];
                        } else if (answer.length() == dp[i + p[j]].length() && answer.compareTo(dp[i + p[j]]) < 0) {
                            answer = dp[i + p[j]];
                        }
                    }
                }
            }
        }

    }
}
