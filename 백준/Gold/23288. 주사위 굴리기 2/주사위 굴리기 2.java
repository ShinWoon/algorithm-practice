import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static int N, M, K, dir = 2, addScore = 0;
	public static int[][] map;
	// 서 북 동 남
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,-1,0,1};
	public static int[][] dice = {{2}, {4,1,3}, {5} ,{6}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		K = Integer.parseInt(tmp[2]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			tmp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		// k만큼 이동할 예정
		// 먼저 이동 -> 해당 위치에서 bfs로 같은 값 다 찾기 => addScore += map[i][j] * 칸 수
		// 주사위 값, map[i][j] 값 비교 => dir 지정
		int x = 0 , y = 0;
		for(int i=0; i<K; i++) {
			x += dx[dir];
			y += dy[dir];
			
			if(x<0 || x>=M || y<0 || y>=N) {
				// map에서 벗어나는 경우 반대 방향으로 바꾸고 진행 
				x -= dx[dir];
				y -= dy[dir];
				dir = (dir+2)%4;
				x += dx[dir];
				y += dy[dir];
			}
			
			addScore += map[y][x] * getCount(x,y,map[y][x]);
			changeDice();
			changeDir(x, y);
		}
		System.out.println(addScore);
	}
	
	public static int getCount(int x, int y, int num) {
		int cnt = 1;
		boolean[][] visited = new boolean[N][M];
		visited[y][x] = true;
		
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {x,y});
		
		while(!q.isEmpty()) {
			Integer[] xy = q.poll();
			x = xy[0];
			y = xy[1];
			
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				
				if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
				if(map[cy][cx] == num && !visited[cy][cx]) {
					q.offer(new Integer[] {cx,cy});
					visited[cy][cx] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void changeDice() {
		if(dir == 0) {
			int tmp = dice[3][0];
			dice[3][0] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = tmp;
			
		} else if (dir == 1) {
			// 북 
			int tmp = dice[0][0];
			dice[0][0] = dice[1][1];
			dice[1][1] = dice[2][0];
			dice[2][0] = dice[3][0];
			dice[3][0] = tmp;
			
		} else if (dir == 2) {
			int tmp = dice[3][0];
			dice[3][0] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = tmp;
			
		} else {
			int tmp = dice[3][0];
			dice[3][0] = dice[2][0];
			dice[2][0] = dice[1][1];
			dice[1][1] = dice[0][0];
			dice[0][0] = tmp;
		}
	}
	
	public static void changeDir(int x, int y) {
		if(dice[3][0] > map[y][x]) dir = (dir+1)%4;
		else if (dice[3][0] < map[y][x]) dir = (dir+3)%4;
	}
}