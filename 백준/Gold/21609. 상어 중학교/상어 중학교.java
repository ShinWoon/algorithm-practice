import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static class BlockInfo {
		int x;
		int y;
		int rainbowCnt;
		int blockSize;
		List<Integer[]> spots;
		public BlockInfo(int x, int y, int rainbowCnt, int blockSize, List<Integer[]> spots) {
			super();
			this.x = x;
			this.y = y;
			this.rainbowCnt = rainbowCnt;
			this.blockSize = blockSize;
			this.spots = spots;
		}
		@Override
		public String toString() {
			return "BlockInfo [x=" + x + ", y=" + y + ", rainbowCnt=" + rainbowCnt + ", blockSize=" + blockSize
					+ "]";
		}
		
	}
	static int N, M, score;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	static List<BlockInfo> blockList;
	static BlockInfo pickedBlock;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			tmp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		solve();
		System.out.println(score);
	}
	
	public static void solve() {
		score = 0;
		while(true) {
			visited = new boolean[N][N];
			blockList = new ArrayList<>();
			pickedBlock = new BlockInfo(N, N, 0, 0, new ArrayList<>());
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if((map[i][j] != 0 && map[i][j] != -1 && map[i][j] != -2) && !visited[i][j]) {
						// 0 rainbow, -1 black, -2 blank
						findBlockGroup(j, i, map[i][j]);
					}
				}
			}
			if(blockList.size() == 0) break;
//			printList();
//			BlockInfo selectedBlock = selectBlock();
//			System.out.println(selectedBlock);
//			score += Math.pow(selectedBlock.blockSize, 2);
			score += Math.pow(pickedBlock.blockSize, 2);
			deleteBlock(pickedBlock);
//			printMap();
			gravity();
//			printMap();
			rotate();
//			printMap();
			gravity();
//			printMap();
		}
	}
	
	public static void findBlockGroup(int x, int y, int blockNum) {
		int cnt = 0, rainbowCnt = 0;
		int startX = x, startY = y;
		List<Integer[]> blockSpots = new ArrayList<>();
		List<Integer[]> rainbowSpots = new ArrayList<>();
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {x,y});
		blockSpots.add(new Integer[] {x,y});
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Integer[] xy = q.poll();
			x = xy[0];
			y = xy[1];
			cnt++;
			blockSpots.add(new Integer[] {x,y});
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if(cx<0 || cx>=N || cy<0 || cy>=N) continue;
				if((map[cy][cx] == blockNum || map[cy][cx] == 0) && !visited[cy][cx]) {
					visited[cy][cx] = true;
					q.offer(new Integer[] {cx,cy});
					if(map[cy][cx] == 0) {
						rainbowCnt++;
						rainbowSpots.add(new Integer[] {cx,cy});
					}
				}
			}
		}
		// 그룹은 블록 2개 이상일 때 부터 
		if(cnt>=2) {
			if(pickedBlock.blockSize < cnt) {
				pickedBlock = new BlockInfo(startX, startY, rainbowCnt, cnt, blockSpots);
			} else if (pickedBlock.blockSize == cnt) {
				if(pickedBlock.rainbowCnt < rainbowCnt) pickedBlock = new BlockInfo(startX, startY, rainbowCnt, cnt, blockSpots);
				else if (pickedBlock.rainbowCnt == rainbowCnt) {
					if(pickedBlock.y < startY) pickedBlock = new BlockInfo(startX, startY, rainbowCnt, cnt, blockSpots);
					else if (pickedBlock.y == startY && pickedBlock.x < startX) pickedBlock = new BlockInfo(startX, startY, rainbowCnt, cnt, blockSpots);
				}
			}
			blockList.add(new BlockInfo(startX, startY, rainbowCnt, cnt, blockSpots));
		}
		// 무지개 블록 방문 취소 시키기 
		for(int i=0; i<rainbowSpots.size(); i++) {
			Integer[] xy = rainbowSpots.get(i);
			visited[xy[1]][xy[0]] = false;
		}
	}
	// 객체 리스트 정렬해서 원하는 블록 객체 가지고 오기
	public static BlockInfo selectBlock() {
		Comparator<BlockInfo> comparator = new Comparator<BlockInfo>() {

			@Override
			public int compare(BlockInfo o1, BlockInfo o2) {
				if(o1.blockSize > o2.blockSize) {
					return o2.blockSize - o1.blockSize;
				} else if (o1.blockSize == o2.blockSize) {
					if(o1.rainbowCnt > o2.rainbowCnt) {
						return o2.rainbowCnt - o1.rainbowCnt;
					} else if(o1.rainbowCnt == o2.rainbowCnt) {
						if(o1.y > o2.y) {
							return o2.y - o1.y;
						} else if (o1.y == o2.y && o1.x> o2.x) {
							return o2.x - o1.x;
						}
					}
				}
				return 0;
			}
		};
		Collections.sort(blockList, comparator);
		return blockList.get(0);
	}
	// 정해진 블록 객체 내에 있는 spot 리스트 돌면서 해당 칸에 빈칸인 -2로 지정 
	public static void deleteBlock(BlockInfo selectedBlock) {
		List<Integer[]> deleteSpot = selectedBlock.spots;
		for(int i=0; i<deleteSpot.size(); i++) {
			Integer[] xy = deleteSpot.get(i);
			map[xy[1]][xy[0]] = -2;
		}
	}
	
	// 4 * 4
	// 0,0 -> 0,3, 1,0 -> 0,2
	// 90 반시계 방향으로 회전 
	public static void rotate() {
		int[][] tmpMap = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmpMap[N-1-j][i] = map[i][j];
			}
		}
		
		// 얕은 복사 방지 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	// 중력 작용 
	public static void gravity() {
		// 열을 돌면서 중력 작용 
		for(int i=0; i<N; i++) {
			for(int j=N-2; j>=0; j--) {
				if(map[j][i] >= 0) {
					int idx = j+1, num = map[j][i];
					while(map[idx][i] == -2) {
						map[idx][i] = num;
						map[idx-1][i] = -2;
						idx++;
						if(idx == N) break;
					}
				}
			}
		}
	}
	
	public static void printList() {
		for(int i=0; i<blockList.size(); i++) System.out.print(blockList.get(i) + " ");
		System.out.println("");
	}
	
	public static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) System.out.print(map[i][j] + " ");
					System.out.println("");
		}
		System.out.println("");
	}
}