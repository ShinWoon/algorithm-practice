import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M, fuel, startX, startY, remainFuel, passengerCnt;
	static int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
	static int[][] map, passenger, destination;
	static boolean[][] visited, dVisited;
	static List<Integer[]> customerList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		fuel = Integer.parseInt(tmp[2]);

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		tmp = br.readLine().split(" ");
		startY = Integer.parseInt(tmp[0]) - 1;
		startX = Integer.parseInt(tmp[1]) - 1;

		passenger = new int[M][2];
		destination = new int[M][2];
		for (int i = 0; i < M; i++) {
			tmp = br.readLine().split(" ");
			passenger[i][1] = Integer.parseInt(tmp[0]) - 1;
			passenger[i][0] = Integer.parseInt(tmp[1]) - 1;
			destination[i][1] = Integer.parseInt(tmp[2]) - 1;
			destination[i][0] = Integer.parseInt(tmp[3]) - 1;
		}

		solve();
		if (remainFuel == -1)
			System.out.println(-1);
		else
			System.out.println(remainFuel);
	}

	public static void solve() {
		remainFuel = fuel;
		passengerCnt = 0;
		setMap();
		for(int i=0; i<M; i++) {
			customerList = new ArrayList<>();
			findPassenger(startX, startY);
			if(customerList.size() == 0) {
				remainFuel = -1;
				return;
			}
//			System.out.println(remainFuel);
			Integer[] info = customerList.get(customerList.size()-1);
			goToDestination(info[3], info[0], info[1]);
			if(remainFuel == -1) return;
//			System.out.println("after " +remainFuel);
		}
	}

	public static void findPassenger(int x, int y) {
		int cnt = 0;
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] { x, y, cnt });
		visited = new boolean[N][N];
		visited[y][x] = true;
		int px = 0, py = 0, minDist = (int)1e9, index = 0;

		while (!q.isEmpty()) {
			Integer[] info = q.poll();
			x = info[0];
			y = info[1];
			cnt = info[2];
//			System.out.println(x + " " + y);
//			System.out.println(cnt + "---");
			if (map[y][x] > 1) {
				// 승객이 있는 경우
				if(minDist > cnt) {
					minDist = cnt;
					py = y;
					px = x;
					index = map[y][x] -2 ;
				} else if(minDist == cnt) {
					if(py > y) {
						py = y;
						px = x;
						index = map[y][x] -2 ;
					} else if(py == y && x < px) {
						px = x;
						index = map[y][x] -2 ;
					}
				}
				customerList.add(new Integer[] {px, py, minDist, index});
			}
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if (cx < 0 || cx >= N || cy < 0 || cy >= N)
					continue;
				if (map[cy][cx] != 1 && !visited[cy][cx]) {
					visited[cy][cx] = true;
					q.offer(new Integer[] { cx, cy, cnt + 1 });
				}
			}
		}
		remainFuel -= minDist;
		map[py][px] = 0;
	}

	public static void goToDestination(int index, int x, int y) {
		boolean checkPossible = false;
		int desX = destination[index][0];
		int desY = destination[index][1];
		int dCnt = 0;
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] { x, y, dCnt });
		dVisited = new boolean[N][N];
		dVisited[y][x] = true;

		while (!q.isEmpty()) {
			Integer[] info = q.poll();
			x = info[0];
			y = info[1];
			dCnt = info[2];
//			System.out.println(dCnt);
			if (dCnt > remainFuel) {
				remainFuel = -1;
				return;
			}
			if (x == desX && y == desY) {
				checkPossible = true;
				startX = desX;
				startY = desY;
				if (remainFuel >= dCnt) {
					remainFuel += dCnt;
				} else {
					remainFuel = -1;
				}
				return;
			}
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if (cx < 0 || cx >= N || cy < 0 || cy >= N)
					continue;
				if (map[cy][cx] != 1 && !dVisited[cy][cx]) {
					dVisited[cy][cx] = true;
					q.offer(new Integer[] { cx, cy, dCnt + 1 });
				}
			}

		}

		remainFuel = -1;
	}

	public static void setMap() {
		for (int i = 0, idx = 2; i < M; i++) {
			int x = passenger[i][0];
			int y = passenger[i][1];
			map[y][x] = idx++;
		}
	}
}