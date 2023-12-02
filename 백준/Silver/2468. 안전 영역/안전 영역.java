import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static int N;
	public static int[][] drawn;
	public static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int max_level = 0;

		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
				max_level = Math.max(max_level, map[i][j]);
			}
		}
		
		int max_area = 0;
		for (int i = 0; i <= max_level; i++) {
			drawn = new int[N][N];
			visited = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (map[row][col] <= i)
						drawn[row][col] = 1;
				}
			}
			int area_cnt = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (drawn[row][col] == 0 && visited[row][col] == false) {
						dfs(col, row);
						area_cnt++;
					}
				}
			}
			max_area = Math.max(max_area, area_cnt);
		}
		System.out.println(max_area);
	}

	public static void dfs(int x, int y) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		visited[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if (cx >= 0 && cx < N && cy >= 0 && cy < N) {
				if (drawn[cy][cx] == 0 && visited[cy][cx] == false)
					dfs(cx, cy);
			}
		}
	}
}