import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N, resultTime;
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
	static int[][] map;
	static boolean[][] visited;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int startX, startY, endX, endY;
			startY = sc.nextInt();
			startX = sc.nextInt();
			endY = sc.nextInt();
			endX = sc.nextInt();
			
			resultTime = 0;
			solve(startX, startY, endX, endY);
			int result = resultTime;
			if(result == 0) result = -1;	// 만약 방문할 수 없으면 -1 출력 
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void solve(int x, int y, int endX, int endY) {
		int time = 0;
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {x,y,time});
		visited = new boolean[N][N];
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Integer[] info = q.poll();
			x = info[0];
			y = info[1];
			time = info[2];
			if(x == endX && y == endY) {
				resultTime = time;	// 도착하면 최소 걸린 시간 갱신 
				return;
			}
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if(cx<0 || cx>=N || cy<0 || cy>=N) continue;

				if(map[cy][cx] == 0 && !visited[cy][cx]) {	// 만약 아무것도 없는 곳에 방문하면 
					q.offer(new Integer[] {cx, cy, time+1});
					visited[cy][cx] = true;
				} else if (map[cy][cx] == 2 && !visited[cy][cx]) {	// 만약 소용돌이 있는 곳에 방문하면 
					if(time%3 == 2) {
						// 소용돌이 사라지는 시간
						q.offer(new Integer[] {cx, cy, time + 1});	// 소용돌이가 없어지기 위해 지나야 하는 시간 계산해서 더해주기  
						visited[cy][cx] = true;
					} else {
						q.offer(new Integer[] {x,y, time+1});
					}
				}
			}
		}
	}
}