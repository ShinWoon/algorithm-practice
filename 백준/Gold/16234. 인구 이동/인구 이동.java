import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static int N, L, R;
	public static int countrySpot;
	public static int[][] map;
	public static boolean[][] visited;
	public static List<List<Integer[]>> selectedCountry;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		L = Integer.parseInt(tmp[1]);
		R = Integer.parseInt(tmp[2]);

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		int turn = 0;
		while (true) {
			countrySpot = 0;
			selectedCountry = new ArrayList<>();
			findCountry();
			if (countrySpot == 0) {
				break;
			} else {
				setCountry();
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<N; j++) {
//						System.out.print(map[i][j] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("");
				turn++;
			}
		}
		System.out.println(turn);
	}
	
	public static void findCountry() {
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] == false) {
					visited[i][j] = true;
					bfs(i,j);
				}
			}
		}
	}
	
	public static void bfs(int y, int x) {
		int cnt = 0;
		List<Integer[]> spots = new ArrayList<>();
		Queue<Integer[]> que = new LinkedList<>();
		spots.add(new Integer[] {x,y});
		que.offer(new Integer[] {x,y});
		
		while(!que.isEmpty()) {
			Integer[] tmp = que.poll();
			x = tmp[0];
			y = tmp[1];
			
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				
				if(cx<0 || cx>=N || cy<0 || cy>=N) continue;
				if((Math.abs(map[y][x] - map[cy][cx]) >= L && Math.abs(map[y][x] - map[cy][cx]) <= R) &&visited[cy][cx] == false) {
					visited[cy][cx] = true;
					spots.add(new Integer[] {cx,cy});
					que.offer(new Integer[] {cx,cy});
					cnt++;
				}
			}
		}
		
		if(cnt != 0) {
			selectedCountry.add(spots);
			countrySpot += cnt;
		}
	}
	
	public static void setCountry() {
		for(int i=0; i<selectedCountry.size(); i++) {
			int cntP = 0;
			for(int j=0; j<selectedCountry.get(i).size(); j++) {
				int x = selectedCountry.get(i).get(j)[0];
				int y = selectedCountry.get(i).get(j)[1];
				cntP += map[y][x];
			}
			int avgP = cntP/selectedCountry.get(i).size();
			for(Integer[] xy : selectedCountry.get(i)) {
				map[xy[1]][xy[0]] = avgP;
			}
		}
	}
}