import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static class XY{
        int x;
        int y;
        int time;

        public XY(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    private static int N, M, TOTAL;
    private static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<XY> startQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        N = Integer.parseInt(tmp[1]);
        TOTAL = 0;  // 안익은 토마토 수

        startQ = new LinkedList<>();
        visited = new boolean[N][M];
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
                if(map[i][j] == 1) {    // 익은 토마토는 큐에 입력
                    startQ.add(new XY(j, i, 0));
                    visited[i][j] = true;
                } else if(map[i][j] == 0) { // 안익은 토마토 수를 확인하기 위해
                    TOTAL++;
                }
            }
        }

        int result = solve();
        System.out.println(result);
    }

    private static int solve() {
        int currentTime = 0;
        while (!startQ.isEmpty()) {
            XY xy = startQ.poll();
            int x = xy.x;
            int y = xy.y;
            currentTime = xy.time;

//            System.out.println("X " + x + " Y " + y);

            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
                if(map[cy][cx] == 0 && !visited[cy][cx]) {
                    startQ.add(new XY(cx, cy, currentTime + 1));
                    visited[cy][cx] = true;
                    TOTAL--;    // 안익은 토마토 수 줄이기
                }
            }
        }
        if(TOTAL == 0) {    // 다 익었을 경우
            return currentTime;
        }
        return -1;  // 다 안익으면
    }
}