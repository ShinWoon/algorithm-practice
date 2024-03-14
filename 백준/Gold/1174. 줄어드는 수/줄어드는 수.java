import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int N;
    private static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    private static List<Long> numList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new ArrayList<>();

        solution(0, 0);
        Collections.sort(numList);

        if (N > numList.size()) System.out.println(-1);
        else System.out.println(numList.get(N - 1));
    }

    private static void solution(long num, int idx) {
        if (!numList.contains(num)) {
            numList.add(num);
        }

        if(idx == 10) return;
        solution(num, idx+1);
        solution(num * 10 + arr[idx] , idx+1);
    }
}