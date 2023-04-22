import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static int[][] map, cntMap;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        N = Integer.parseInt(tmp[1]);

        // M = 가로 , N = 세로
        map = new int[N][M];
        cntMap = new int[N][M];
        for(int i=0; i<N; i++) {
            String inputstr = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt("" + inputstr.charAt(j));
                cntMap[i][j] = (int)1e9;
            }
        }
        int result = solve();
        System.out.println(result);
    }

    // 다익스트라
    public static int solve() {
        int x = 0, y = 0;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{x,y});  // 0,0에서 시작
        cntMap[y][x] = 0;

        while(!q.isEmpty()) {
            Integer[] xy = q.poll();
            x = xy[0];
            y = xy[1];

            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
//                System.out.println(cx + " " + cy);
                if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
                if(cntMap[cy][cx] > cntMap[y][x] + map[cy][cx]) {
                    cntMap[cy][cx] = cntMap[y][x] + map[cy][cx];
                    q.add(new Integer[]{cx, cy});
                }
            }
        }
        return cntMap[N-1][M-1];    // N,M으로 이동하기 위해 부셔야 하는 최소 벽의 값
    }
}