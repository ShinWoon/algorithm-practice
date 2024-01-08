import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, RESULT;
    private static String[] strArr, checkArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        RESULT = 0;

        // S 집합 문자열 N개 받기
        strArr = new String[N];
        for(int i=0; i<N; i++) {
            strArr[i] = br.readLine();
        }
        // 접두사 M개
        checkArr = new String[M];
        for(int i=0; i<M; i++) {
            checkArr[i] = br.readLine();
        }

        // 이분탐색을 위해 일단 정렬
        Arrays.sort(strArr);
        Arrays.sort(checkArr);

        checkPrefix();  // M개 문자열 중 접두사로 포함되는 거 확인
        System.out.println(RESULT);
    }
    private static void checkPrefix(){  // 알파벳 순서 정렬된 N개 문자열 순서 이분탐색으로 보고 접두사랑 일치하는 거 찾아내기
        for(int i=0; i<M; i++) {
            String prefix = checkArr[i];    // 이번 접두사
            int start = 0;
            int end = N;
            int middle;

            while(start<end) {
                middle = (start+end)/2;
                String currentStr = strArr[middle].substring(0,prefix.length());    // 이번에 확인할 문자열을 접두사 크기 만큼 잘라서 보기
//                System.out.println("prefix: " + prefix + "    string: " + currentStr);
                if(currentStr.compareTo(prefix) < 0) {  // 만약 음수면 알파벳 상 더 앞이니까 뒤에 탐색하러
                    start = middle+1;
                } else if(currentStr.compareTo(prefix) > 0) {   // 양수 알파벳 뒤에 있으니 앞에 탐색하러
                    end = middle;
                } else {    // 일치하는거 찾으면 결과 값 증가
                    RESULT++;
                    break;  // 확인 다 했으니 끊기 <- 이런 실수 좋지 않아
                }
            }
        }
    }
}