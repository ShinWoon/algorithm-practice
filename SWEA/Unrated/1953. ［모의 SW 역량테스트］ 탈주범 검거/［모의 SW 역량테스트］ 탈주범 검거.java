import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {
    static int N, M, R, C, L, result;
    static int[][] map;
    static boolean[][] pipeInfo = { // 상 , 우, 하, 좌 <- 상하, 좌우는 2씩 차이
            {},
            {true, true, true, true},
            {true, false, true, false},
            {false, true, false, true},
            {true, true, false, false},
            {false, true, true, false},
            {false, false, true, true},
            {true, false, false, true}
    };
    static int[] dx = {0,1,0,-1}, dy = {-1,0,1,0};  // 상, 우, 하, 좌
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();

            map = new int[N][M];
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            solve();
            System.out.println("#" + test_case + " " + result);
        }
    }

    private static void solve() {
        result = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<Integer[]> q = new LinkedList<>();    //[다음 위치 y, x, depth]
        q.add(new Integer[]{R,C,1});
        visited[R][C] = true;

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            int y = info[0];
            int x = info[1];
            int currentDepth = info[2];
//            System.out.println(currentDepth);
            if(currentDepth <= L) {
                // L 소요 시간 만큼만 갈 수 있으니까
                result += 1;

                // 상하좌우 확인
                for(int i=0; i<4; i++) {
                    int cx = x + dx[i];
                    int cy = y + dy[i];

                    if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
                    if(map[cy][cx] != 0) {
                        // 파이프가 있다면
                        if(!visited[cy][cx]) {
                            // 방문하지 않은 곳
                                if(pipeInfo[map[y][x]][i] && pipeInfo[map[cy][cx]][(i+2)%4]) {
                                    // 내가 현재 있는 파이프의 상하좌우 중 true인 경우 다음 파이프의 +2 위치가 true 이면 갈 수 있음
                                    q.add(new Integer[]{cy, cx, currentDepth + 1});
                                    visited[cy][cx] = true;
                                }
                        }
                    }
                }
            } else return;
        }
    }
}