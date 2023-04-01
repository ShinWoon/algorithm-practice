import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static class Fireball{
		int r;
		int c;
		int m;
		int s;
		int d;
		
		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		public int getM() {
			return m;
		}
		public void setM(int m) {
			this.m = m;
		}
		public int getS() {
			return s;
		}
		public void setS(int s) {
			this.s = s;
		}
		public int getD() {
			return d;
		}
		public void setD(int d) {
			this.d = d;
		}
	}
	static int N, M, K;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // 방향 0,1,2,3,4,5,6,7에 따라 설정
	static List<Integer>[][] map;
	static List<Fireball> fireballList; // 파이어볼 정보 저장 list
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		K = Integer.parseInt(tmp[2]);
		
		map = new ArrayList[N][N];
		fireballList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			tmp = br.readLine().split(" ");
			int r = Integer.parseInt(tmp[0])-1;
			int c = Integer.parseInt(tmp[1])-1;
			fireballList.add(new Fireball(r, c, Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4])));
		}
		
		solve();
		int answer = getRemains();	// 남아있는 파이어볼 질량 합 
		System.out.println(answer);
	}
	
	public static void solve() {
		for(int turn=0; turn<K; turn++) {
			setMap();	// map 초기화 
			order();	// 상어 명령 함수 
			checkFireball();	// 파이어볼 모두 움직이고 난 후 결과값 갱신
		}
	}
	
	public static void order() {
		for(int i=0; i<fireballList.size(); i++) {
			moveFireball(fireballList.get(i), i);
		}
	}
	
	// 파이어볼 이동 처리 함수 
	public static void moveFireball(Fireball currentFireball, int idx) {
		int y = currentFireball.getR();
		int x = currentFireball.getC();
		int speed = currentFireball.getS();
		int dir = currentFireball.getD();
		
		for(int i=0; i<speed; i++) {
			x += dx[dir];
			y += dy[dir];
			// 행 또는 열이 범위 밖에 벗어나는 경우. 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있으므로 값을 처리.
			if(x < 0) x = N-1;
			else if(x >= N) x = 0;
			if(y < 0) y = N-1;
			else if(y >=N) y= 0;
		}
		currentFireball.setR(y);
		currentFireball.setC(x);
		map[y][x].add(idx);	//map에 현재 처리 중인 파이어볼 인덱스 값 저장 
	}
	public static void checkFireball() {
		List<Fireball> tmpList = new ArrayList<>();
		// map을 돌며 이번 turn 파이어볼 결과 값 갱신 
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x].size() == 1) tmpList.add(fireballList.get(map[y][x].get(0)));
				else {
					// 만약 중복되는 파이어볼이 있는 경우 
					int massSum = 0, speedSum = 0, size = map[y][x].size();
					boolean checkEven = true, checkOdd = true;
					int[] decidedDir = new int[4];
					for(int i=0; i<size; i++) {
						massSum += fireballList.get(map[y][x].get(i)).getM();
						speedSum +=fireballList.get(map[y][x].get(i)).getS();
						// 파이어볼이 모두 짝수인지 또는 모두 홀수인지 check
						if(fireballList.get(map[y][x].get(i)).getD()%2 != 0) checkEven = false;
						else if (fireballList.get(map[y][x].get(i)).getD()%2 == 0) checkOdd = false;
					}
					if(checkEven || checkOdd) {
						// 모두 짝수 or 홀수인 경우 
						for(int i=0, didx=0; i<7; i+=2, didx++) decidedDir[didx] = i;
					} else {
						for(int i=1, didx=0; i<8; i+=2, didx++) decidedDir[didx] = i;
					}
					// 질량이 0인 경우 소멸되므로 제외 
					if(massSum/5 != 0) {
						for(int i=0; i<4; i++) {
							tmpList.add(new Fireball(y, x, massSum/5, speedSum/size, decidedDir[i]));
						}
					}
				}
			}
		}
		fireballList = tmpList;
	}
	// map 초기화 
	public static void setMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
	}
	// 남아 있는 파이어볼 질량 합 계산 
	public static int getRemains() {
		int remainedMass = 0;
		for(int i=0; i<fireballList.size(); i++) remainedMass += fireballList.get(i).getM();
		return remainedMass;
	}
}