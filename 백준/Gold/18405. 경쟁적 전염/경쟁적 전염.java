import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class VirusInfo {
        int x;
        int y;
        int type;
        int time;

        public VirusInfo(int x, int y, int type, int time) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.time = time;
        }
    }

    private static int N, K, S, X, Y;
    private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    private static int[][] virusMap;
    private static Queue<VirusInfo> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        virusMap = new int[N][N];
        q = new PriorityQueue<>(new Comparator<VirusInfo>() {
            @Override
            public int compare(VirusInfo o1, VirusInfo o2) {
                if(o1.time == o2.time) {
                    return o1.type - o2.type;
                }
                return o1.time - o2.time;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                virusMap[i][j] = Integer.parseInt(st.nextToken());
                if(virusMap[i][j] != 0) q.add(new VirusInfo(i, j, virusMap[i][j], 0));
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        solution();
        System.out.println(virusMap[X][Y]);
    }

    private static void solution() {
        while (!q.isEmpty()) {
            VirusInfo virusInfo = q.poll();
            if(virusInfo.time == S) return;

            for(int i=0; i<4; i++) {
                int cx = virusInfo.x + dx[i];
                int cy = virusInfo.y + dy[i];

                if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
                if(virusMap[cx][cy] == 0) {
                    virusMap[cx][cy] = virusInfo.type;
                    q.add(new VirusInfo(cx, cy, virusInfo.type, virusInfo.time + 1));
                }
            }
//            printMap();
        }
    }

    private static void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(virusMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}