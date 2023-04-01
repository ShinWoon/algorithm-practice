import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	static List<Fireball> fireballList;
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
		int answer = getRemains();
		System.out.println(answer);
	}
	
	public static void solve() {
		for(int turn=0; turn<K; turn++) {
			setMap();
			order();
			checkFireball();
		}
	}
	
	public static void order() {
		for(int i=0; i<fireballList.size(); i++) {
			moveFireball(fireballList.get(i), i);
		}
	}
	
	public static void moveFireball(Fireball currentFireball, int idx) {
		int y = currentFireball.getR();
		int x = currentFireball.getC();
		int speed = currentFireball.getS();
		int dir = currentFireball.getD();
		
		for(int i=0; i<speed; i++) {
			x += dx[dir];
			y += dy[dir];
			if(x < 0) x = N-1;
			else if(x >= N) x = 0;
			if(y < 0) y = N-1;
			else if(y >=N) y= 0;
		}
		currentFireball.setR(y);
		currentFireball.setC(x);
		map[y][x].add(idx);
	}
	public static void checkFireball() {
		List<Fireball> tmpList = new ArrayList<>();
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x].size() == 1) tmpList.add(fireballList.get(map[y][x].get(0)));
				else {
					int massSum = 0, speedSum = 0, size = map[y][x].size();
					boolean checkEven = true, checkOdd = true;
					int[] decidedDir = new int[4];
					for(int i=0; i<size; i++) {
						massSum += fireballList.get(map[y][x].get(i)).getM();
						speedSum +=fireballList.get(map[y][x].get(i)).getS();
						if(fireballList.get(map[y][x].get(i)).getD()%2 != 0) checkEven = false;
						else if (fireballList.get(map[y][x].get(i)).getD()%2 == 0) checkOdd = false;
					}
					if(checkEven || checkOdd) {
						for(int i=0, didx=0; i<7; i+=2, didx++) decidedDir[didx] = i;
					} else {
						for(int i=1, didx=0; i<8; i+=2, didx++) decidedDir[didx] = i;
					}
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
	public static void setMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
	}
	public static int getRemains() {
		int remainedMass = 0;
		for(int i=0; i<fireballList.size(); i++) remainedMass += fireballList.get(i).getM();
		return remainedMass;
	}
	
//	public static void printMap() {
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N ;j++) System.out.print(map[i][j] + " ");
//			System.out.println("");
//		}
//		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N ;j++) {
//				for(int k=0; k<map[i][j].size(); k++) System.out.print(fireballList.get(k).getM());
//			}
//		}
//		System.out.println("");
//	}
}