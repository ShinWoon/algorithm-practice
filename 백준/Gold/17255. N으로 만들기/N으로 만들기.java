import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static int N, M;
    private static String number;
    private static String[] num;
    private static boolean[] visited;
    private static Set<String> resultSet;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = String.valueOf(N);
        M = number.length();
        num = new String[M];
        for (int i = 0; i < M; i++) num[i] = String.valueOf(number.charAt(i));

        resultSet = new HashSet<>();
        visited = new boolean[M];
        String[] tmpArr = new String[M];
        for (int i = 0; i < M; i++) {
            visited[i] = true;
            tmpArr[0] = num[i];
            solution(1, i-1, i+1, tmpArr);
            visited[i] = false;
        }
        System.out.println(resultSet.size());
    }

    private static void solution(int n, int start, int end, String[] resultArr) {
        if (n == M) {
            resultSet.add(Arrays.toString(resultArr));
            return;
        }

        // 앞으로 붙이기
        if (start >= 0) {
            if (!visited[start]) {
                visited[start] = true;
                resultArr[n] = num[start] + resultArr[n-1];
                solution(n + 1, start - 1, end, resultArr);
                visited[start] = false;
            }
        }
        // 뒤에 붙이기
        if(end < M) {
            if (!visited[end]) {
                visited[end] = true;
                resultArr[n] = resultArr[n-1] + num[end];
                solution(n + 1, start, end+1, resultArr);
                visited[end] = false;
            }
        }
    }
}