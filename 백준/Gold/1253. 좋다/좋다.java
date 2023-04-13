import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, goodNumberCnt;
	static int[] numberArr; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numberArr = new int[N];
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<N; i++) numberArr[i] = Integer.parseInt(tmp[i]);
		
		solve();
		System.out.println(goodNumberCnt);
	}
	public static void solve() {
		goodNumberCnt = 0;
		
		Arrays.sort(numberArr);
		int start=0, end=N-1, sum = 0;
		// 투 포인터 사용 => 특정합을 가지는지 확인하기 위해 숫자 정하기
		for(int i=0; i<N; i++) {
			int currentNumber = numberArr[i];
			start = 0;
			end = N-1;
			sum = numberArr[start] + numberArr[end];
			while(start<end) {
//				System.out.println("currentNumber " + currentNumber + " sum " + sum);
				// 값이 작으면 
				if(sum < currentNumber) {
					sum -= numberArr[start++];
					sum += numberArr[start];
				} else if (sum == currentNumber) {
//					System.out.println("currentNumber " + currentNumber + " startNum " + numberArr[start] + " endNumber" + numberArr[end]);
					if(start != i && end != i) {
						goodNumberCnt++;	// GOOD number 만족 
						break;
					} else if (start == i){
						sum -= numberArr[start++];
						sum += numberArr[start];
					} else if (end == i) {
						sum -= numberArr[end--];
						sum += numberArr[end];
					}
				} else if (sum > currentNumber){
					sum -= numberArr[end--];
					sum += numberArr[end];
				}
			}
		}
		
	}
}