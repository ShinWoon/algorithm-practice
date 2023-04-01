import java.util.Scanner;

public class Solution {
	public static int N, X, possibleRoad;
	public static int[][] map;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			X = sc.nextInt();
			possibleRoad = 0;
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			solve();
			System.out.println("#" + test_case + " " + possibleRoad);
		}
	}
	
	public static void solve() {
		// 가로 조사 
		int[] road = new int[N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				road[j] = map[i][j];
			}
			if(checkPossibility(road)) possibleRoad++;
		}
		
		// 세로 조사
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				road[j] = map[j][i];
			}
			if(checkPossibility(road)) possibleRoad++;
		}
	}
	
	public static boolean checkPossibility(int[] road) {
		int cnt = 1;
		for(int i=0; i<N-1; i++) {
			if(Math.abs(road[i+1]-road[i])>1) return false;
			else if(road[i] == road[i+1]) cnt++;
			else if(road[i] < road[i+1]) {
				if(cnt < X) return false;
				cnt = 1;
			}
			else if(road[i] > road[i+1]) {
				if(cnt < 0) return false;
				cnt = 1 - X;
			}
		}
		if(cnt < 0) return false;
		return true;
	}
}