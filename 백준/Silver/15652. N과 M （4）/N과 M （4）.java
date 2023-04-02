import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, M;
	static int[] combinaiton;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		
		combinaiton = new int[M];
		getCombination(1,0);
		bw.flush();
	}
	
	public static void getCombination(int start, int level) throws IOException {
		if(level == M) {
			for(int num: combinaiton) bw.write(num + " ");
			bw.write("\n");
			return;
		}
		
		for(int i=start; i<=N; i++) {
			combinaiton[level] = i;
			getCombination(i, level+1);
		}
	}
	
}