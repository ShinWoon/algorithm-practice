import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K, answer;
    private static String[] strArr;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        if(K < 5) {
            // a, n, t, i, c
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        strArr = new String[N];
        for(int i=0; i<N; i++) {
            strArr[i] = br.readLine();
            int len = strArr[i].length();
            String tmpStr = strArr[i].substring(4, len - 4);
            strArr[i] = tmpStr;
        }

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        answer = 0;
        dfs(0,0);
        System.out.println(answer);
    }
    private static void dfs(int idx, int depth) {
        if(depth == K-5) {
            int cnt = 0;
            for(int i=0; i<N; i++) {
                String currentStr = strArr[i];
                boolean check = true;
                for(int j=0; j<currentStr.length(); j++) {
                    char currentC = currentStr.charAt(j);
                    if(!visited[currentC - 'a']) {
                        check = false;
                        break;
                    }
                }
                if(check) cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }

        for(int i=idx; i<26; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}