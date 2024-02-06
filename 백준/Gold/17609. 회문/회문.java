import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String inputString;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            inputString = br.readLine();

            int result = solve(0, inputString.length() - 1, false);
            System.out.println(result);
        }
    }
    private static int solve(int start, int end, boolean check) {
        while (start < end) {
            if (inputString.charAt(start) == inputString.charAt(end)) {  // 값이 같은 경우
                // 앞 뒤 둘 다 한 칸 씩 이동하자
                start++;
                end--;
            } else {    // 값이 다른 경우
                if(!check) {
                    check = true;
                    if(solve(start+1, end, check) == 0 || solve(start, end-1, check) == 0) {
                        return 1;
                    } else return 2;
                } else return 2;
            }
        }
        return 0;
    }
}