import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static int N, maxNum = 0;
	public static int[] numArr;
	public static Map<String, Integer> alphabet;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		alphabet = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			int idx = (int) Math.pow(10, (word.length()-1));
			for(int j=0; j<word.length(); j++) {
				String w = "" + word.charAt(j);
				if(alphabet.containsKey(w))
					alphabet.put(w, alphabet.get(w) + 1*(idx));
				else
					alphabet.put(w, 1*(idx));
				idx /= 10;
			}
		}

		solve();
		
		System.out.println(maxNum);
	}
	public static void solve() {
		numArr = new int[alphabet.size()];
		List<String> mapKey = new ArrayList<>(alphabet.keySet());
		for(int i=0; i<mapKey.size(); i++) {
			numArr[i] = alphabet.get(mapKey.get(i));
		}
		
		Arrays.sort(numArr);
		int n = 9;
		for(int i=numArr.length-1; i>=0; i--) {
			maxNum += numArr[i] * (n--);
		}
	}
}