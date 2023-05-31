import java.io.*;
import java.util.PriorityQueue;

public class Main {
    private static class ForestInfo{
        int num;
        int x;
        int y;
        public ForestInfo(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    private static int N, maxVisit;
    private static int[] dx = {-1,0,1,0} , dy = {0,-1,0,1};
    private static int[][] map, cntMap;
    private static boolean[][] visited;
//    private static PriorityQueue<ForestInfo> forestQ;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
//        forestQ = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            String tmp[] = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
//                forestQ.add(new ForestInfo(map[i][j], j, i));
            }
        }

        solve();
        System.out.println(maxVisit+1);
    }

    private static void solve() {
        cntMap = new int[N][N];
        visited = new boolean[N][N];
        maxVisit = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                    visited[i][j] = true;
                    int result = dfs(i,j);
                    maxVisit = Math.max(maxVisit, result);
//                    System.out.print(result + " ");
            }
//            System.out.println();
        }
    }

    private static int dfs(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            if (cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
            if (map[cy][cx] > map[y][x]) {
                // 현재보다 더 많은 양
                if (!visited[cy][cx]) {
                    visited[cy][cx] = true;
                    cntMap[y][x] = Math.max(cntMap[y][x], dfs(cy, cx)+1);
                } else {
                    cntMap[y][x] = Math.max(cntMap[y][x], cntMap[cy][cx] + 1);
                }
            }
        }
        return cntMap[y][x];
    }
}