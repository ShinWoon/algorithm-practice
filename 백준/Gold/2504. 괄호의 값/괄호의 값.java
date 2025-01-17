import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int result = solve(str);
        System.out.println(result);
    }

    private static int solve(String str) {
        int result = 0;
        Stack<Character> stack = new Stack<>();
        int calcNum = 1;
        for(int i=0; i<str.length(); i++) {
            char currentC = str.charAt(i);
            if(currentC == '(') {
                calcNum *= 2;
                stack.push(currentC);
            } else if (currentC == '[') {
                calcNum *= 3;
                stack.push(currentC);
            } else if (currentC == ')') {
                if(stack.isEmpty()) {
                    return 0;
                } else if(stack.peek() == '(') {
                    stack.pop();
                }
                if(i!=0 && str.charAt(i-1) == '(') {
                    result += calcNum;
                }
                calcNum /= 2;
            } else {
                if(stack.isEmpty()) {
                    return 0;
                } else if(stack.peek() == '[') {
                    stack.pop();
                }
                if(i!=0 && str.charAt(i-1) == '[') {
                    result += calcNum;
                }
                calcNum /= 3;
            }
        }
        if(!stack.isEmpty()) return 0;
        return result;
    }
}
