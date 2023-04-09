import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int N, K, P, X, digitMaxNum, answer;
	public static int[] stopNum, newNum;
	public static int[][] numChange;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		P = Integer.parseInt(tmp[2]);
		X = Integer.parseInt(tmp[3]);
		
		digitMaxNum = Integer.parseInt("" + tmp[0].charAt(0));
		stopNum = new int[K];
		for(int i=K-1, j=tmp[3].length()-1; j>=0; j--, i--)
			stopNum[i] = Integer.parseInt("" + tmp[3].charAt(j));
//		printArr();
		solve();
		System.out.println(answer);
	}
	
	public static void solve() {
		numChange = new int[10][10];
		setNumChange();
		
		newNum = new int[K];
		answer = 0;	// 자기 자신과 같은 번호가 나올 경우 제거 
		getNewNum(0);
	}
	public static void getNewNum(int level) {
		if(level == K) {
			boolean check = checkValiable();
			if(check) answer++;
			return;
		}
		int maxNum = 9;
		if(level == 0) maxNum = digitMaxNum;
		for(int i=0; i<=maxNum; i++) {
			newNum[level] = i;
			getNewNum(level+1);
		}
	}
	
//	public static void printArr() {
//		for(int i=0; i<K; i++) {
//			System.out.print(stopNum[i] + " ");
//		}
//		System.out.println("");
//	}
	
	public static boolean checkValiable() {
		if(!checkNum()) return false;
		int changedCnt = 0;
		for(int i=0; i<K; i++) {
			changedCnt += numChange[stopNum[i]][newNum[i]];
		}
//		System.out.println("change " + changedCnt);
		if(changedCnt<=P) return true;
		return false;
	}
	
	public static boolean checkNum() {
		int number = 0;
		for(int i=0; i<K; i++) {
			number += newNum[i] * Math.pow(10, K-1-i);
		}
//		System.out.println(number);
		if(number == X || number == 0) return false;
		if(number <= N) return true;
		return false;
	}
	public static void setNumChange() {
		int[][] ledNum = {{1,1,1,1,1,1,0}
						, {0,1,1,0,0,0,0}
						, {1,1,0,1,1,0,1}
						, {1,1,1,1,0,0,1}
						, {0,1,1,0,0,1,1}
						, {1,0,1,1,0,1,1}
						, {1,0,1,1,1,1,1}
						, {1,1,1,0,0,0,0}
						, {1,1,1,1,1,1,1}
						, {1,1,1,1,0,1,1}};
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(i==j) numChange[i][j] = 0;
				else {
					int cnt=0;
					for(int h=0; h<7; h++) {
						if(ledNum[i][h] != ledNum[j][h]) cnt++;
					}
					numChange[i][j] = cnt;
				}
			}
		}
	}
}