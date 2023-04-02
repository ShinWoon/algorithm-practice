import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] permutation;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		
		permutation = new int[M];
		getPermutation(1,0);
	}
	
	// 조합 
	public static void getPermutation(int start, int level) {
		if(level == M) {
			printArr();
			return;
		}
		for(int i=start; i<=N; i++) {
			permutation[level] = i;
			getPermutation(i+1, level+1);
		}
	}
	
	public static void printArr() {
		for(int i=0; i<M; i++) System.out.print(permutation[i] + " ");
		System.out.println("");
	}
}