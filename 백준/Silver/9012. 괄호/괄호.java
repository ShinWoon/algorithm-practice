import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            String result = solve(stack, str);
            bw.append(result).append("\n");
        }
        bw.flush();
    }
    private static String solve(Stack stack, String str) {
        for(int i=0; i<str.length(); i++) {
            char currentC = str.charAt(i);
            if(stack.isEmpty()) {
                stack.push(currentC);
            } else {
                if(currentC == ')') {
                    char peekC = (char)stack.peek();
                    if(peekC == '(') {
                        stack.pop();
                    } else {
                        stack.push(currentC);
                    }
                } else {
                    stack.push(currentC);
                }
            }
        }
        if(stack.isEmpty()) return "YES";
        return "NO";
    }
}
