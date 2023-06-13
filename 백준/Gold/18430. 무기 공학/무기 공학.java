import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean[][] visited;
    private static int N, M, boomerang;
    private static int[][] materials, dx = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}, dy = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        materials = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                materials[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        solve();
        System.out.println(boomerang);
    }

    private static void solve() {
        boomerang = 0;
        visited = new boolean[N][M];
        getBoomerang(0, 0, 0);
    }

    private static void getBoomerang(int x, int y, int materialCnt) {
        boomerang = Math.max(materialCnt, boomerang);   // 매번 최대 부메랑 강도 합 갱신
        if (x >= M) {
            x = 0;
            y += 1;
        }
        if (y >= N) {
            return;
        }

        if (!visited[y][x]) {
            // 방문 안한 재료 사용
            int midMaterial = materials[y][x] * 2;  // 중간 애는 * 2
            visited[y][x] = true;
            for (int type = 0; type < 4; type++) {   // 부메랑 만들기는 4가지 방법 존재
                int cy1 = y + dy[type][0];
                int cx1 = x + dx[type][0];
                int cy2 = y + dy[type][1];
                int cx2 = x + dx[type][1];
                if (checkXy(cx1, cy1) && checkXy(cx2, cy2)) {    // 조건 모두 만족
                    visited[cy1][cx1] = true;
                    visited[cy2][cx2] = true;
                    getBoomerang(x + 1, y, materialCnt + (midMaterial + materials[cy1][cx1] + materials[cy2][cx2]));
                    visited[cy1][cx1] = false;
                    visited[cy2][cx2] = false;
                }
            }
            visited[y][x] = false;
        }
        // 걍 돌리기
        getBoomerang(x + 1, y, materialCnt);

    }

    private static boolean checkXy(int x, int y) {  // 가능한 방법인지 확인
        if (x < 0 || x >= M || y < 0 || y >= N) return false;
        if (visited[y][x]) return false;
        return true;
    }


}