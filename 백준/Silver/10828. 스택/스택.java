import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static Stack<Integer> stack;
    private static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String func = st.nextToken();
            int num = 0;
            if(func.equals("push")) {
                num = Integer.parseInt(st.nextToken());
            }
            solve(func, num);
        }
        bw.flush();
    }
    private static void solve(String func, int num) throws Exception {
        switch(func) {
            case "push":
                stack.push(num);
                break;
            case "pop":
                if(stack.isEmpty()) {
                    bw.append("-1");
                } else {
                    int currentNum = stack.pop();
                    bw.append(String.valueOf(currentNum));
                }
                bw.append("\n");
                break;
            case "size":
                int size = stack.size();
                bw.append(String.valueOf(size)).append("\n");
                break;
            case "empty":
                if(stack.isEmpty()) {
                    bw.append("1");
                } else {
                    bw.append("0");
                }
                bw.append("\n");
                break;
            case "top":
                if(stack.isEmpty()) {
                    bw.append("-1");
                } else {
                    int currentNum = stack.peek();
                    bw.append(String.valueOf(currentNum));
                }
                bw.append("\n");
                break;
            default:
                break;
        }
    }
}