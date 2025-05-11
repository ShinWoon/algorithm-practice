import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String startStr = br.readLine();
        String endStr = br.readLine();

        result = 0;
        solve(startStr, endStr);
        System.out.println(result);
    }
    private static void solve(String startStr, String endStr) {
        if(startStr.length() == endStr.length()) {
            if(startStr.equals(endStr)) {
                result = 1;
            }
        }

        // 문자열이 A로 끝나는 경우
        if(endStr.endsWith("A")) {
            String nextStr = endStr.substring(0, endStr.length()-1);
            solve(startStr, nextStr);
        }

        // 문자열이 B로 시작하는 경우
        if(endStr.startsWith("B")) {
            String nextStr = endStr.substring(1);
            nextStr = new StringBuilder(nextStr).reverse().toString();
            solve(startStr, nextStr);
        }
    }
}
