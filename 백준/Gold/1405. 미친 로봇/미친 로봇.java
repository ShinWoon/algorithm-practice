import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static double result;
    private static double[] dirPercentage;
    private static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dirPercentage = new double[4];
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        for(int i=0; i<4; i++) {
            dirPercentage[i] = (double) Integer.parseInt(tmp[i+1]) /100;
        }

        result = 0;
        visited = new boolean[203][203];
        visited[100][100] = true;
        solve(100,100,0, 1.0);
        System.out.println(result);   // 10-9까지 출력
    }

    private static void solve(int y, int x, int move, double percentage) {
        if(move == N) {
            result += percentage;
            return;
        }

        for(int i=0; i<4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            if(!visited[cy][cx]) {
                visited[cy][cx] = true;
                solve(cy, cx, move + 1, percentage * dirPercentage[i]);
                visited[cy][cx] = false;
            }
        }
    }
}