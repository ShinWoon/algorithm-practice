import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, result, maxNum;
    private static List<Integer> positive, negative;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        maxNum = 0;
        positive = new ArrayList<>();
        negative = new ArrayList<>();
        tmp = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(tmp[i]);
            maxNum = Math.max(maxNum, Math.abs(num));
            if(num < 0) negative.add(num);
            else positive.add(num);
        }

        result = 0;
        solve();
        System.out.println(result);
    }

    private static void solve() {
        Collections.sort(negative);
        Collections.sort(positive);
        Collections.reverse(positive);

        for(int i=0; i<negative.size(); i++) {
//            System.out.print(negative.get(i) + " ");
            if(i%M == 0) {
                if(Math.abs(negative.get(i)) == maxNum) result += maxNum;
                else result += (Math.abs(negative.get(i))*2);
            }
        }

//        System.out.println();

        for(int i=0; i<positive.size(); i++) {
//            System.out.print(positive.get(i) + " ");
            if(i%M == 0) {
                if(Math.abs(positive.get(i)) == maxNum) result += maxNum;
                else result += (Math.abs(positive.get(i))*2);
            }
        }

//        System.out.println();
    }
}