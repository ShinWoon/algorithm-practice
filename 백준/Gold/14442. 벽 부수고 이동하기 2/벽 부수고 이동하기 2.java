import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K, answer;
    private static int[] dx = {0,-1,0,1}, dy = {-1,0,1,0};
    private static int[][] map;
    private static class Info {
        int x;
        int y;
        int wallCnt;

        Info(int x, int y, int wallCnt) {
            this.x = x;
            this.y = y;
            this.wallCnt = wallCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j-1)));
            }
        }
        
        solve();
        if(answer == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(answer);
    } 
    private static void solve() {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(1, 1, 0));

        int[][][] visited = new int[K+1][N+1][M+1];
        visited[0][1][1] = 1;

        while (!q.isEmpty()) {
            Info current = q.poll();

            if(current.y == N && current.x == M) {
                answer = Math.min(answer, visited[current.wallCnt][N][M]);
                continue;
            }

            for(int i=0; i<4; i++) {
                int cx = current.x + dx[i];
                int cy = current.y + dy[i];

                if(cx<=0 || cx>M || cy <=0 || cy>N) continue;
                if(map[cy][cx] == 0) {
                    if(visited[current.wallCnt][cy][cx] == 0) {
                        q.add(new Info(cx, cy, current.wallCnt));
                        visited[current.wallCnt][cy][cx] = visited[current.wallCnt][current.y][current.x] + 1;
                    }
                } else {
                    if(current.wallCnt + 1 <= K) {
                        if(visited[current.wallCnt + 1][cy][cx] == 0) {
                            q.add(new Info(cx, cy, current.wallCnt + 1));
                            visited[current.wallCnt+1][cy][cx] = visited[current.wallCnt][current.y][current.x] + 1;
                        }
                    }
                }
            }
        }
    }
}
