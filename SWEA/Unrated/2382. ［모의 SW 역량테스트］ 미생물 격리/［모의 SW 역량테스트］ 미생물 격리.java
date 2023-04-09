import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	static class MicrobeGroup {
		int num;
		int y;
		int x;
		int microbeCnt;
		int dir;
		public MicrobeGroup(int num, int y, int x, int microbeCnt, int dir) {
			super();
			this.num = num;
			this.y = y;
			this.x = x;
			this.microbeCnt = microbeCnt;
			this.dir = dir;
		}
	}
	static int N, M, K, result, idx;
	static int[] dx = {0,0,0,-1,1}, dy = {0,-1,1,0,0};	// 상, 하, 좌, 우 
	static Map<Integer, MicrobeGroup> groupList;
	static List<Integer>[][] map;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			idx = 1;
			groupList = new HashMap<>();
			map = new ArrayList[N][N];
			
			for(int i=0; i<K; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				int mCnt = sc.nextInt();
				int d = sc.nextInt();
				groupList.put(idx, new MicrobeGroup(idx, y, x, mCnt, d));
				idx++;
			}
			
			setInitialMap();	// map 초기화 & 미생물 군집 위치 찍기 
			solve();
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void solve() {
		int time = 0;
		while(true) {
			if(time == M) {	// 시간이 M이면 멈추고 남아있는 미생물들 수 구하기 
				countMicrobes();
				break;
			}
			moveGroup();	// 미생물 군집 이동 
			setInitialMap();	// map 초기화 & 미생물 군집 위치 찍기 
			setMap();			// 조건에 따른 후처리하기 
			time++;
		}
	}
	
	public static void moveGroup() {
		Set<Integer> key = groupList.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) {
			int gNumber = (int) iter.next();
			MicrobeGroup mg = groupList.get(gNumber);
			 mg.x += dx[mg.dir];
			mg.y += dy[mg.dir];
			groupList.put(gNumber, mg);
		}
	}
	
	public static void setInitialMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		// 미생물 군집 위치 찍기 
		Set<Integer> key = groupList.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) {
			int gNumber = (int) iter.next();
			MicrobeGroup mg = groupList.get(gNumber);
			map[mg.y][mg.x].add(gNumber);
		}
	}
	
	public static void setMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size() == 0) continue;
				if(i == 0 || i == N-1 || j == 0 || j == N-1) {
					if(map[i][j].size() > 0) {
						// 약품 처리된 곳에 있는 경우 
						MicrobeGroup tmpMg = groupList.get(map[i][j].get(0));
						tmpMg.microbeCnt /= 2;
						if(tmpMg.microbeCnt == 0) {	// 0이면 군집 없애기 
							groupList.remove(tmpMg.num);
						}
						else {
							// 아니라면 방향 반대로 바꿔주고 저장 
							tmpMg.dir = changeDir(tmpMg.dir);
							groupList.put(tmpMg.num, tmpMg);							
						}
					}
				}
				// 여러개 모인 경우 
				if(map[i][j].size() > 1) {
					int sum = 0, d = 0, maxMicro = 0;
					for(int s=0; s<map[i][j].size(); s++) {
						int n = map[i][j].get(s);
//						System.out.println(n);
						sum += groupList.get(n).microbeCnt;
						if(groupList.get(n).microbeCnt > maxMicro) {	// 방향은 미생물 수가 많은 군집 방향으로 설정 
							maxMicro = groupList.get(n).microbeCnt;
							d = groupList.get(n).dir;
						}
						groupList.remove(n);
					}
					// 합친 군집은 새로운 숫자 key로 저장 
					map[i][j] = new ArrayList<>();
					map[i][j].add(idx);
					groupList.put(idx, new MicrobeGroup(idx, i, j, sum, d));
					idx++;
				}
			}
		}
	}
	// 방향은 반대로 설정 상 1 하 2 좌 3 우 4 
	public static int changeDir(int dir) {
		if(dir == 1) return 2;
		else if(dir == 2) return 1;
		else if (dir == 3) return 4;
		else return 3;
	}
	// 남아있는 미생물 수 구하기 
	public static void countMicrobes() {
		result = 0;
		Set<Integer> key = groupList.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) {
			int gNumber = (int) iter.next();
			result += groupList.get(gNumber).microbeCnt;
		}
	}
}