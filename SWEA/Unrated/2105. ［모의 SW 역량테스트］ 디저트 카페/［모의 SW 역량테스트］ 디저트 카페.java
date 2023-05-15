import java.util.Scanner;

public class Solution {
    static boolean[] visited;
    static int N, maxEaten;
    static int[] dx = {1,-1,-1,1}, dy = {1, 1, -1, -1};
    static int[][] map;
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            map = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) map[i][j] = sc.nextInt();
            }
            solve();
            System.out.println("#" + test_case + " " + maxEaten);
        }
    }

    private static void solve() {
        maxEaten = -1;
        visited = new boolean[101];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                visited[map[i][j]] = true;
                dfs(i,j, i, j, 1, 0);
                visited[map[i][j]] = false;
            }
        }
    }

    private static void dfs(int startY, int startX, int y, int x, int eaten, int dir) {
//        if(x == startX && y == startY && eaten>=2) {
//            System.out.println(eaten);
//            maxEaten = Math.max(maxEaten, eaten);
//            return;
//        }
        // 처음 위치 갈 수가 없음....

//        visited[map[y][x]] = true;
//        for(int i=0; i<4; i++) {
//            int cx = x + dx[i];
//            int cy = y + dy[i];
//
//            if(cx>=0 && cx<N && cy>=0 && cy<N) {
//
//            }
//        }
//        System.out.println(eaten);
        int cx = x + dx[dir];
        int cy = y + dy[dir];
        if(cx>=0 && cx<N && cy>=0 && cy<N) {
            if(!visited[map[cy][cx]]){
                visited[map[cy][cx]] = true;
                dfs(startY, startX, cy, cx, eaten+1, dir);
                visited[map[cy][cx]] = false;
            } else if (cy == startY && cx == startX && eaten >=4) {
                maxEaten = Math.max(maxEaten, eaten);
                return;
            }
        }

        if(dir+1==4) return;
        cx = x + dx[dir+1];
        cy = y + dy[dir+1];
        if(cx>=0 && cx<N && cy>=0 && cy<N) {
            if(!visited[map[cy][cx]]){
                visited[map[cy][cx]] = true;
                dfs(startY, startX, cy, cx, eaten+1, dir+1);
                visited[map[cy][cx]] = false;
            } else if (cy == startY && cx == startX && eaten >=4) {
                maxEaten = Math.max(maxEaten, eaten);
                return;
            }
        }
    }
}