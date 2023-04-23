import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int N, M, H, validate = 0;
	public static int[][] info;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		H = Integer.parseInt(tmp[2]);

		info = new int[N][];
		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split(" ");
			info[i] = new int[tmp.length + 1];
			for (int j = 0; j < tmp.length; j++) {
				info[i][j + 1] = Integer.parseInt(tmp[j]);
			}
		}

		solve();
		System.out.println(validate);
	}

	public static void solve() {
		int[][] dp = new int[N][H + 1];

		for(int i=0; i<info[0].length; i++) dp[0][info[0][i]] = 1;
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < info[i].length; j++) {
				for (int h = 0; h <= H; h++) {
					if (H >= h + info[i][j]) {
						dp[i][h + info[i][j]] += (dp[i - 1][h]) % 10007;
					}
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < H + 1; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println("");
//		}
		validate = dp[N-1][H] % 10007;
	}
}