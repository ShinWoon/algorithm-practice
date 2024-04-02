import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};  // 상하좌우
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Integer[]> xy = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    // 에어컨 자리
                    xy.add(new Integer[]{j,i});
                }
            }
        }
        if (xy.size() == 0) {
            // 에어컨 없으면
            System.out.println(0);
        } else {
            visited = new boolean[N][M];
            solution(xy);
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) cnt += 1;
                }
            }
            System.out.println(cnt);
        }
    }

    private static void solution(List<Integer[]> xy) {
        Queue<Integer[]> q = new LinkedList<>();
        for(int i=0; i<xy.size(); i++) {
            int x = xy.get(i)[0];
            int y = xy.get(i)[1];
            for (int j = 0; j < 4; j++) {
                // 시작할 때 4방향으로 먼저
                q.add(new Integer[]{x, y, j});    // x, y, 방향
            }
        }

        while (!q.isEmpty()) {
            Integer[] tmpArr = q.poll();
            int x = tmpArr[0];
            int y = tmpArr[1];
            int dir = tmpArr[2];

            visited[y][x] = true;

            int cx = x + dx[dir];
            int cy = y + dy[dir];
            if (!check(cx, cy)) continue;  // 범위 벗어남

            if (map[cy][cx] == 1) {    // 1번 물건
                if (dir == 0 || dir == 1) {  // 상, 하
                    q.add(new Integer[]{cx, cy, dir});
                } else {
                    visited[cy][cx] = true;
                }
            } else if (map[cy][cx] == 2) { // 2번 물건
                if (dir == 2 || dir == 3) {
                    q.add(new Integer[]{cx, cy, dir});
                } else {
                    visited[cy][cx] = true;
                }
            } else if (map[cy][cx] == 3) { // 3번 물건
                if (dir == 0) {
                    q.add(new Integer[]{cx, cy, 3});
                } else if (dir == 1) {
                    q.add(new Integer[]{cx, cy, 2});
                } else if (dir == 2) {
                    q.add(new Integer[]{cx, cy, 1});
                } else {
                    q.add(new Integer[]{cx, cy, 0});
                }
            } else if (map[cy][cx] == 4) { // 4번 물건
                if (dir == 0) {
                    q.add(new Integer[]{cx, cy, 2});
                } else if (dir == 1) {
                    q.add(new Integer[]{cx, cy, 3});
                } else if (dir == 2) {
                    q.add(new Integer[]{cx, cy, 0});
                } else {
                    q.add(new Integer[]{cx, cy, 1});
                }
            } else if (map[cy][cx] == 0) { // 0번
                q.add(new Integer[]{cx, cy, dir});
            }
        }
    }

    private static boolean check(int x, int y) {
        if (x < 0 || x >= M || y < 0 || y >= N) return false;
        return true;
    }
}