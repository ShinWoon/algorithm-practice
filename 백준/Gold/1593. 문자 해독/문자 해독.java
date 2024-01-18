import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int g, absS;
    private static char[] W, S;
    private static int[] visitedS, visitedW;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        g = Integer.parseInt(st.nextToken());
        absS = Integer.parseInt(st.nextToken());

        String input = br.readLine();
        W = new char[g];
        for(int i=0; i<g; i++) W[i] = input.charAt(i);
        input = br.readLine();
        S = new char[absS];
        for(int i=0; i<absS; i++) S[i] = input.charAt(i);

        int result = solve();
        System.out.println(result);
    }
    private static int solve() {
        int cnt = 0;
        visitedS = new int[123];
        visitedW = new int[123];
        // W 만큼 일단 채우기
        for (int i = 0; i < g; i++) {
            char currentCharW = W[i];
            char currentCharS = S[i];
            visitedW[currentCharW] += 1;
            visitedS[currentCharS] += 1;
        }
//        if (checkSame()) cnt++;
        if(Arrays.equals(visitedS,visitedW)) cnt++;

        int start, end;
        for(int i=1; i<=absS-g; i++) {
            start = i;
            end = i + g - 1;
            visitedS[S[start-1]] -= 1;
            visitedS[S[end]] += 1;
//            if(checkSame()) cnt++;
            if(Arrays.equals(visitedS,visitedW)) cnt++;
        }
        return cnt;
    }
//    private static boolean checkSame() {
//        for(int i=0; i<123; i++) if(visitedS[i] != visitedW[i]) return false;
//        return true;
//    }
}