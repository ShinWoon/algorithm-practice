import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int resultX, resultY;
    private static int[] dx = {0,1,1,-1}, dy = {1,0,1,1};
    private static int[][] map;
    private static boolean[][][] visited;
    private static class Spot {
        int x;
        int y;
        int cnt;
        Spot(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        map = new int[20][20];
        for(int i=1; i<20; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<20; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[20][20][4];

        for(int i=1; i<20; i++) {
            for(int j=1; j<20; j++) {
                if(map[i][j] != 0) {
                    for(int k=0; k<4; k++) {
                        if(!visited[i][j][k]) {
                            int currResult = solve(i, j, map[i][j], k);
                            if(currResult != 0) {
                                sb.append(currResult).append("\n");
                                sb.append(resultY).append(" ").append(resultX);
                                System.out.println(sb);
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }
    private static int solve(int startY, int startX, int type, int dir) {
        Queue<Spot> q = new LinkedList<>();
        q.add(new Spot(startX, startY, 1));
        visited[startY][startX][dir] = true;

        int result = 0;
        while (!q.isEmpty()) {
            Spot currSpot = q.poll();

            if(currSpot.cnt == 5) {
                result = type;
                if(startX > currSpot.x) {
                    resultX = currSpot.x;
                    resultY = currSpot.y;
                } else {
                    resultX = startX;
                    resultY = startY;
                }
            } else if(currSpot.cnt > 5){
                result = 0;
            }

            int cx = currSpot.x + dx[dir];
            int cy = currSpot.y + dy[dir];

            if(cx<1 || cx>19 || cy<1 || cy>19) continue;
            if(map[cy][cx] == type && !visited[cy][cx][dir]) {
                q.add(new Spot(cx, cy, currSpot.cnt + 1));
                visited[cy][cx][dir] = true;
            }
        }
        return result;
    }
}