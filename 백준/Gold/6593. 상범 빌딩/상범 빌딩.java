import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int L, R, C, minTime;
    private static char[][][] map;
    private static int[] startP, endP;
    private static int[] dx = {0,-1,0,1,0,0}, dy = {-1,0,1,0,0,0}, dz = {0,0,0,0,-1,1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        startP = new int[3];    // [z,y,x]
        endP = new int[3];

        while(true) {
            String[] tmp = br.readLine().split(" ");
            L = Integer.parseInt(tmp[0]);
            R = Integer.parseInt(tmp[1]);
            C = Integer.parseInt(tmp[2]);

//            System.out.println("L " + L + " R "+ R + " C " + C);
            if(L == 0 && R == 0 && C == 0) break;   // 입력 끝 조건

            map = new char[L][R][C];
            // 지도 정보 받자
            for(int i=0; i<L; i++) {
                for(int j=0; j<R; j++) {
                    String inputStr = br.readLine();
                    for(int k=0; k<C; k++) {
                        map[i][j][k] = inputStr.charAt(k);
                        if(map[i][j][k] == 'S') {
                            startP[0] = i;
                            startP[1] = j;
                            startP[2] = k;
                        } else if (map[i][j][k] == 'E') {
                            endP[0] = i;
                            endP[1] = j;
                            endP[2] = k;
                        }
                    }
                }
                br.readLine();
            }

//            System.out.println("E : " + endP[0] + " " + endP[1] + " " + endP[2]);
            solve();
            if(minTime == (int)1e9) System.out.println("Trapped!");
            else System.out.println("Escaped in " + minTime + " minute(s).");
        }
    }

    private static void solve() {
        minTime = (int)1e9;
        int z = startP[0];
        int y = startP[1];
        int x = startP[2];
        int timeCnt = 0;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{z, y, x, timeCnt});   // [z,y,x,시간 cnt]
        boolean[][][] visited = new boolean[L][R][C];
        visited[z][y][x] = true;

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            z = info[0];
            y = info[1];
            x = info[2];
            timeCnt = info[3];
            if(z == endP[0] && y == endP[1] && x == endP[2]) {
                minTime = timeCnt;
                break;
            }

            for(int i=0; i<6; i++) {
                int cz = z + dz[i];
                int cy = y + dy[i];
                int cx = x + dx[i];

                if(cx<0 || cx>=C || cy<0 || cy>=R || cz<0 || cz>=L) continue;
                if(!visited[cz][cy][cx] && map[cz][cy][cx] != '#') {
                    // 방문가능한 조건
                    visited[cz][cy][cx] = true;
                    q.add(new Integer[]{cz,cy,cx,timeCnt+1});
                }
            }
        }
    }
}