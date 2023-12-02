import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException  {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];

			arr = br.readLine().split(" ");

		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		String[] check = new String[M];
		check = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			if(Arrays.binarySearch(arr, check[i])>-1) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.print(sb);
	}
}