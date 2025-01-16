import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bw.append("<");

        // 큐 세팅
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            q.add(i);
        }
        
        // 돌면서 K번째 숫자 빼기
        while (!q.isEmpty()) {
            if(q.size() == 1) {
                // 1개 남았을 때
                bw.append(String.valueOf(q.poll())).append(">");
                break;
            }
            for(int i=0; i<K-1; i++) {
                int num = q.poll();
                q.add(num);
            }
            bw.append(String.valueOf(q.poll())).append(", ");
        }

        bw.flush();
    }
}
