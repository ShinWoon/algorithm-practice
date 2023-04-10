import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
//	static class Robot {
//		int x;
//		int y;
//		int d;
//		public Robot(int x, int y, int d) {
//			super();
//			this.x = x;
//			this.y = y;
//			this.d = d;
//		}
//	}
	static int N, M, robotX, robotY, robotDir, cleanedArea;
	static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0}; // 북 동 남 서
	static int[][] map;
	static boolean[][] visited;
//	static Robot cleaningRobot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		
		tmp = br.readLine().split(" ");
		robotY = Integer.parseInt(tmp[0]);
		robotX = Integer.parseInt(tmp[1]);
		robotDir = Integer.parseInt(tmp[2]);
//		cleaningRobot = new Robot(rx, ry, rd);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			tmp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		solve();
		System.out.println(cleanedArea);
	}
	
	public static void solve() {
		cleanedArea = 1;
		visited = new boolean[N][M];
		findArea();
	}
	
	public static void findArea() {
		int x = robotX;
		int y = robotY;
		int d = robotDir;
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] {x,y,d});
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Integer[] info = q.poll();
			x = info[0];
			y = info[1];
			d = info[2];
			
			boolean checkFour = false;
			// 4칸 중에 청소되지 않은 빈칸이 있는지 확인
			for(int i=3; i>=0; i--) {
				int cx = x + dx[(d+i)%4];
				int cy = y + dy[(d+i)%4];
				if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
				if(map[cy][cx] == 0 && !visited[cy][cx]) {
					cleanedArea++;
					q.add(new Integer[] {cx, cy, (d+i)%4});
					visited[cy][cx] = true;
					checkFour = true;
					break;
				}
			}
			// 만약 4칸 중에 청소되지 않은 빈칸이 없는 경우
			if(!checkFour) {
				// 뒤로 한칸 후진할 수 있는지 확인
				int cx = x + dx[(d+2)%4];
				int cy = y + dy[(d+2)%4];
				// 뒤로 한칸 후진할 수 없는 경우 작동 멈추기
				if((cx < 0 || cx>=M || cy<0 || cy>=N) || map[cy][cx] == 1) return;
				else if(map[cy][cx] == 0) {
					q.add(new Integer[] {cx, cy, d});
				}
				
			}
		}
		
	}
}