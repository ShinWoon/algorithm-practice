import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static int N, result;
    private static Stack<Integer> info;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        info = new Stack<>();
        result = 0;
        for(int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            solve(y);
        }

        while(!info.isEmpty()) {
            if(info.pop() > 0) result++;
        }
        System.out.println(result);
    }

    private static void solve(int height) {
        while(!info.isEmpty()) {
            if(height < info.peek()) {
                result++;
                info.pop();
            } else break;
        }
        if(info.isEmpty() || (!info.isEmpty() && height != info.peek())) info.add(height);
    }
}