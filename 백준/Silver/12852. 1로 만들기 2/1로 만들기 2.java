import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        int[] func = new int[N+1];
        func[N] = 0;

        for(int i=N; i>1; i--) {
            if(i%3 == 0) { // 3으로 나눠지면
                if(dp[i/3] > dp[i] + 1) {   // 작으면 사용한 방식 수정
                    dp[i/3] = dp[i] + 1;
                    func[i/3] = 3;
                }
            }
            if(i%2 == 0) {  // 2로 나눠지면
                if(dp[i/2] > dp[i] + 1) {   // 작으면 사용한 방식 수정
                    dp[i/2] = dp[i] + 1;
                    func[i/2] = 2;
                }
            }
            if(dp[i-1] > dp[i] + 1) {   // 작으면 사용한 방식 수정
                dp[i-1] = dp[i] + 1;
                func[i-1] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append("\n");
        // N -> 1까지의 과정을 보여주기 위해 스택에 저장해서 출력
        Stack<Integer> stack = new Stack<>();
        int n = 1;
        while(true) {
            stack.push(n);
            if(n == N) break;
            if(func[n] == 1) {  // 1이면 아까 -1 했다는 뜻
                n += 1;
            } else if(func[n] == 2) {   // 2이면 아까 /2 했다는 뜻
                n *= 2;
            } else {    // 3이면 아까 /3 했다는 뜻
                n *= 3;
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        System.out.println(sb);
    }
}