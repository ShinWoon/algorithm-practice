import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int N;
    private static List<String> resultList;
    private static int[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0; test_case<T; test_case++) {
            String inputStr = br.readLine();
            N = inputStr.length();
            resultList = new ArrayList<>();
            visited = new int[26];
            for(int i=0; i<N; i++) {
                char c = inputStr.charAt(i);
                visited[c - 'a'] += 1;
            }
            // 애너그램 생성
            solution(0, new char[N]);

            // 애러그램 출력
            for(String str: resultList) sb.append(str).append("\n");
        }
        System.out.println(sb);
    }
    private static void solution(int n, char[] arr) {
        if(n == N) {
            // 시간 초과 해결? -> X
            StringBuilder sb = new StringBuilder();
            for(char c: arr) sb.append(c);
            resultList.add(String.valueOf(sb));
            return;
        }

        for(int i=0; i<26; i++) {
            if(visited[i] != 0) {
                visited[i] -= 1;
                arr[n] = (char)(i + 'a');
                solution(n+1, arr);
                visited[i] += 1;
            }
        }
    }
}