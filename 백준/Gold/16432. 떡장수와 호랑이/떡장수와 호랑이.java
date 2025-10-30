import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean live;
    private static boolean[][] visited;
    private static int N;
    private static int[] answer;
    private static int[][] tteok;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tteok = new int[N][];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            tteok[i] = new int[M];
            for(int j=0; j<M; j++) {
                tteok[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][10];
        answer = new int[N];
        live = false;
        dfs(0, new int[N]);

        StringBuilder sb = new StringBuilder();
        if(live) {
            for(int i=0; i<N; i++) {
                sb.append(answer[i]).append("\n");
            }
        } else {
            sb.append("-1");
        }
        System.out.println(sb);
    }
    private static void dfs(int depth, int[] tteokOrder) {
        if(depth == N) {
            answer = tteokOrder.clone();
            live = true;
            return;
        }

        for(int i=0; i<tteok[depth].length; i++) {
            int currentTteok = tteok[depth][i];
            if(depth != 0) {
                // 전날 떡 종류 확인하기
                if(tteokOrder[depth-1] == currentTteok) continue;
            }
            if(!visited[depth][currentTteok]) {
                visited[depth][currentTteok] = true;
                tteokOrder[depth] = currentTteok;
                dfs(depth+1, tteokOrder);
            }
        }
    }
}
