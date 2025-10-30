import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        System.out.println(solve());
    }
    private static String solve() {
        // 자리수 구하기
        int len = 1, num = 0, idx = 0;
        while(true) {
            int before = num;
            num += Math.pow(2, len);
            if(K <= num) {
                idx = K - before - 1;
                break;
            }
            len++;
        }

        // 이진수로 변경
        String bin = Integer.toBinaryString(idx);
        String tmp = bin;
        if(bin.length() < len) {
            for(int i=0; i<len-bin.length(); i++) {
                tmp = "0" + tmp;
            }
        }

        // 4,7로 변경
        String answer = "";
        for(int i=0; i<tmp.length(); i++) {
            if(tmp.charAt(i) == '0') answer += "4";
            else answer += "7";
        }

        return answer;
    }
}
