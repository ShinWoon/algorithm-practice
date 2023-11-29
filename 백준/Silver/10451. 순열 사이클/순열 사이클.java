import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int N, RESULT;
    private static int[] numArr;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test_case=0; test_case<T; test_case++) {
            N = Integer.parseInt(br.readLine());
            numArr = new int[N+1];
            
            // 서로의 관계 값 저장
            String[] tmp = br.readLine().split(" ");
            for(int i=0; i<N; i++) numArr[i+1] = Integer.parseInt(tmp[i]);
            
            solve();
            System.out.println(RESULT);
        }
    }

    private static void solve() {
        RESULT = 0;
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {   // 만약 방문하지 않으면 순회 가능한거 확인
                visit(i);
                RESULT++;   // 방문 끝나면 사이클 하나 추가
            }
        }
    }

    private static void visit(int start){
        visited[start] = true;
        int next = numArr[start];
        if(!visited[next]) {    // 만약 다음 방문한 적 없으면 재귀
            visit(next);
        }
    }
}