import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int lastNum;
    private static Queue<Integer> q;
    private static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        lastNum = -1;

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
    private static void solve(String func, int num) throws Exception{
        switch (func) {
            case "push":
                q.add(num);
                lastNum = num;
                break;
            case "pop":
                if(!q.isEmpty()) {
                    int currentNum = q.poll();
                    bw.append(String.valueOf(currentNum));
                } else {
                    bw.append("-1");
                }
                bw.append("\n");
                break;
            case "size":
                int size = q.size();
                bw.append(String.valueOf(size)).append("\n");
                break;
            case "empty":
                if(!q.isEmpty()) {
                    bw.append("0");
                } else {
                    bw.append("1");
                }
                bw.append("\n");
                break;
            case "front":
                if(!q.isEmpty()) {
                    int currentNum = q.peek();
                    bw.append(String.valueOf(currentNum));
                } else {
                    bw.append("-1");
                }
                bw.append("\n");
                break;
            case "back":
                if(!q.isEmpty()) {
                    bw.append(String.valueOf(lastNum));
                } else {
                    bw.append("-1");
                }
                bw.append("\n");
            break;
            default:
                break;
        }
    }
}
