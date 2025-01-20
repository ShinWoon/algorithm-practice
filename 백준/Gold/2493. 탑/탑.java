import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static class Info {
        int idx;
        int height;
        Info(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        Stack<Info> stack = new Stack<>();
        for(int i=0; i<N; i++) {
            int currentHeight = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()) {
                bw.append("0");
                stack.push(new Info(i+1, currentHeight));
            } else {
                Info tmpInfo = stack.peek();
                if(tmpInfo.height < currentHeight) {
                    while(!stack.isEmpty()) {
                        tmpInfo = stack.peek();
                        if(tmpInfo.height >= currentHeight) break;
                        stack.pop();
                    }
                    if(stack.isEmpty()) {
                        bw.append("0");
                    } else {
                        bw.append(String.valueOf(tmpInfo.idx));
                    }
                } else {
                    bw.append(String.valueOf(tmpInfo.idx));
                }
                stack.push(new Info(i+1, currentHeight));
            }
            bw.append(" ");
        }

        bw.flush();
    }
}