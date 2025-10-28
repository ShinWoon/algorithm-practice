import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, T;
    private static int[] dx={0,0,1,-1}, dy={-1,1,0,0};
    private static int[][] map;
    private static class Point {
        int x;
        int y;
        int time;
        boolean hasSword;
        Point(int x, int y, int time, boolean hasSword) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.hasSword = hasSword;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
    private static String solve() {
        int[][] visited = new int[N][M];
        visited[0][0]++;
        int x = 0, y = 0, time = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, time, false));
        
        while (!q.isEmpty()) {
            Point currentP = q.poll();
            x = currentP.x;
            y = currentP.y;
            time = currentP.time;

            if(x == M-1 && y == N-1) {
                if(time <= T) {
                    return Integer.toString(time);
                } else {
                    return "Fail";
                }
            }

            for(int i=0; i<4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if(cx>=0 && cx<M && cy>=0 && cy<N) {
                    if(currentP.hasSword) {
                        if(visited[cy][cx] <= 1) {
                            visited[cy][cx]++;;
                            q.add(new Point(cx, cy, time+1, currentP.hasSword));
                        }
                    } else {
                        if(visited[cy][cx] == 0) {
                            if(map[cy][cx] == 0) {
                                visited[cy][cx]++;
                                q.add(new Point(cx, cy, time+1, currentP.hasSword));
                            } else if (map[cy][cx] == 2) {
                                visited[cy][cx]++;
                                q.add(new Point(cx, cy, time+1, true));
                            }
                        }   
                    }
                }
            }
        }
        return "Fail";
    }
}