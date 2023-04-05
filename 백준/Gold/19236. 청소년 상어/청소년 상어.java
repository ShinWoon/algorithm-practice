import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
	static class Fish {
		int number;
		int x;
		int y;
		int dir;

		public Fish(int number, int x, int y, int dir) {
			super();
			this.number = number;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static int maxEaten;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 }, dy = { -1, -1, 0, 1, 1, 1, 0, -1 };	// 1~8까지 방향
	static int[][] map;
	static Fish[] fishInfo = new Fish[17];	//물고기의 숫자,x,y,방향 저장 
	static boolean[] dead;	// 물고기가 먹혔는지 확인

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][4];
		for (int i = 0; i < 4; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0, bidx = 0; j < 4; j++) {
				int n = Integer.parseInt(tmp[bidx++]);
				int d = Integer.parseInt(tmp[bidx++]) - 1;
				map[i][j] = n;
				fishInfo[n] = new Fish(n, j, i, d);
			}
		}
		solve(0, 0, 0);
		System.out.println(maxEaten);
	}

	public static void solve(int sharkX, int sharkY, int sharkD) {
		// 상어는 0,0에서 시작하므로 값을 초기화 해줌 
		dead = new boolean[17];
		dead[map[0][0]] = true;
		maxEaten = map[0][0];
		sharkD = fishInfo[map[0][0]].dir;
		map[0][0] = 0;
		
		huntFish(maxEaten, sharkX, sharkY, sharkD);	// 상어 물고기 사냥 
	}

	public static void huntFish(int eatenSum, int sharkX, int sharkY, int sharkD) {
		maxEaten = Math.max(maxEaten, eatenSum);	// 최대로 먹을 수 있는 물고기 값 갱신 

		// 그냥 dfs 돌리니까 값이 제대로 돌아오지 않음. 다시 값을 돌려야하는 map, fishInfo, dead 값을 다른 곳에 잠시 저장.
		// 그냥 복사하니까 얕은 복사되어 깊은 복사 처리해줌. 
		int[][] newMap = new int[4][4];
		for(int i=0; i<4; i++) newMap[i] = map[i].clone();
		
		Fish[] tmpFish = new Fish[17];
		for(int i=1; i<17; i++) tmpFish[i] = new Fish(fishInfo[i].number, fishInfo[i].x, fishInfo[i].y, fishInfo[i].dir);
		
		boolean[] tmpDead = new boolean[17];
		tmpDead = dead.clone();
		
		// 물고기 이동 
		moveFish(sharkX, sharkY);
		
		for (int i = 1; i < 4; i++) {	// 배열이 4*4이므로 상어는 갈 수 있는 경우가 최대 3개 
			int cx = sharkX + dx[sharkD] * i;
			int cy = sharkY + dy[sharkD] * i;
			// 상어는 map 범위 벗어나거나 0인 곳은 갈 수 없으므로 처리 
			if (cx < 0 || cx >= 4 || cy < 0 || cy >= 4)
				break;
			if (map[cy][cx] == 0)
				continue;

			// 먹을 수 있는 조건을 만족할 경우 
			int fish = map[cy][cx];
			map[cy][cx] = 0;
			map[sharkY][sharkX] = 0;
			dead[fish] = true;
			huntFish(eatenSum + fish, cx, cy, fishInfo[fish].dir);
			dead[fish] = false;
//			map[sharkY][sharkX] = 0;
			map[cy][cx] = fish;
		}
		
		// 돌아왔을 떼 값을 다시 돌려 놓기 
		for(int i=0; i<4; i++) map[i] = newMap[i].clone();
		for(int i=1; i<17; i++) fishInfo[i] = new Fish(tmpFish[i].number, tmpFish[i].x, tmpFish[i].y, tmpFish[i].dir);
		dead = tmpDead.clone();
	}

	public static void moveFish(int sharkX, int sharkY) {
		for (int fishN = 1; fishN <= 16; fishN++) {	// 물고기 번호는 1~16까지 존재 
			if(dead[fishN]) continue;	// 만약 먹혔으면 PASS
			Fish currentFish = fishInfo[fishN];
			int fx = currentFish.x;
			int fy = currentFish.y;
			int fd = currentFish.dir;
			int mx, my;
			for (int i = 0; i < 8; i++) {
				mx = fx + dx[(fd + i) % 8];
				my = fy + dy[(fd + i) % 8];
				// 범위 벗어나거나 상어가 있는 곳은 갈 수 없으므로 처리 
				if (mx < 0 || mx >= 4 || my < 0 || my >= 4)
					continue;
				if (mx == sharkX && my == sharkY)
					continue;
				int tmp = map[my][mx];
				map[my][mx] = fishN;
				map[fy][fx] = tmp;
				currentFish.dir =( fd + i) % 8;
				currentFish.x = mx;
				currentFish.y = my;
				fishInfo[fishN] = currentFish;
				if(tmp != 0) {	// 만약 이동한 곳에 물고기가 있었던 경우 
					fishInfo[tmp].x = fx;
					fishInfo[tmp].y = fy;
				}
				break;
			}

		}
	}
}