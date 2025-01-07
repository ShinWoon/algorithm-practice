import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<Long> resultList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N == 0) {
            System.out.println("0");
            return;
        }

        resultList = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            for(int j=9; j>=0; j--) {
                long[] num = new long[i];
                num[0] = j;
                solve(i-1, num, j, 1);
            }
        }

        Collections.sort(resultList);

        if(N < resultList.size()) {
            System.out.println(resultList.get(N));
        } else {
            System.out.println("-1");
        }
    }

    private static void solve(int depth, long[] num, int beforeNum, int idx) {
        if(depth == 0) {
            String number = "";
            for(int i=0; i<num.length; i++) {
                number += String.valueOf(num[i]);
            }
            resultList.add(Long.parseLong(number));
            return;
        }
        if(beforeNum == 0) {
            return;
        }
        for(int i=beforeNum-1; i>=0; i--) {
            num[idx] = i;
            solve(depth-1, num, i, idx+1);
        }

    }
}
