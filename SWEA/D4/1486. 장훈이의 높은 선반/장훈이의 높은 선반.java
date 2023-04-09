import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N, B, minHeight;
	static int[] height;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			B = sc.nextInt();
			
			height = new int[N];
			minHeight = (int)1e9;
			for(int i=0; i<N; i++) height[i] = sc.nextInt();
			solve();
			int result = Math.abs(minHeight-B);
			System.out.println("#" + test_case + " " + result);
		}
	}
	public static void solve() {
		int sum = 0;
		for(int i=1; i< (1<<N); i++) {
			sum = 0;
			for(int j=0; j<N; j++) {
				if((i&(1<<j)) != 0) sum += height[j];
			}
			if(sum >= B) minHeight = Math.min(minHeight, sum);
		}
	}
}