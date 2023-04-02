import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] combArr;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		visited = new boolean[N+1];
		combArr = new int[M];
		getComb(0);
	}
	// 순열 
	public static void getComb(int level) {
		if(level == M) {
			printArr();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combArr[level] = i;
			getComb(level+1);
			visited[i] = false;
		}
	}
	// 결과값 출력 
	public static void printArr() {
		for(int i=0; i<M; i++) System.out.print(combArr[i] + " ");
		System.out.println("");
	}
}