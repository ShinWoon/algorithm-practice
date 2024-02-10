import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] dp;
    private static String sentence;
    private static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sentence = br.readLine();
        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        solution();

        if(dp[sentence.length()] == 2500) System.out.println(-1);
        else System.out.println(dp[sentence.length()]);
    }

    private static void solution() {
        dp = new int[sentence.length() + 1];
        Arrays.fill(dp, 2500);  // Integer.MAX_VALUE 쓰면 오버플로우 일어남
        dp[0] = 0;

        String[] tmpWords; // <- 문장에서 검사할 단어들 임시 저장 배열
        for(int i=1; i<=sentence.length(); i++) {
            tmpWords = new String[i];
            // 이번에 i번 인덱스까지 확인
            for(int j=0; j<i; j++) {
                tmpWords[j] = sentence.substring(j,i);
            }

            for(int j=0; j<i; j++) {    // 이번에 분해한 문자열을 기준으로 해석 단어들과 일치하는거 찾기
                for(int k=0; k<N; k++) {
                    if(tmpWords[j].length() != words[k].length()) continue;
                    if(!checkIfAvailable(tmpWords[j], words[k])) continue;
                    // 길이랑 알파벳 다 일치하면 비용 계산하고 저장하기
                    int cost = calcCost(tmpWords[j], words[k]);
                    if(j == 0) {
                        dp[i] = Math.min(dp[i], cost);
                    } else {
                        dp[i] = Math.min(dp[i], cost + dp[j]);
                    }
                }
            }
        }
    }
    private static boolean checkIfAvailable(String s1, String s2) {
        int[] checkArr = new int[26];   // 어떤 알파벳 등장했는지 확인.
        for(int i=0; i<s1.length(); i++) {
            int idx1 = s1.charAt(i) - 'a';  // s1 문자열 알파벳 값
            int idx2 = s2.charAt(i) - 'a';  // s2 문자열 알파벳 값
            checkArr[idx1]++;
            checkArr[idx2]--;
            // -> 이렇게 하면 중복되는 거 소거될 것
        }

        for(int i=0; i<checkArr.length; i++) {
            if(checkArr[i] != 0) return false;  // 만약에 소거 되지 않은 부분이 있다는건 이건 일치하지 않음
        }
        return true;
    }

    private static int calcCost(String s1, String s2) { // 비용 계산
        int costSum = 0;
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) costSum++; // 동일하지 않을 때 비용 발생
        }
        return costSum;
    }
}