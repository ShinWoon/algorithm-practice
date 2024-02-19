import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, RESULT;
    private static int[] dogs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dogs = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxCnt = 0;
        for(int i=0; i<N; i++) {
            dogs[i] = Integer.parseInt(st.nextToken());
            maxCnt = Math.max(maxCnt, dogs[i]);
        }
        RESULT = 0;
        if(maxCnt < 50) {   // 선 여려개
            permutation(new int[N], new boolean[N], 0);
        } else if (maxCnt == 50) {  // 선 하나 밖에 없음
            RESULT = 1;
        }
        // 50 넘으면 중앙 지나는거 하나도 없음
        System.out.println(RESULT);
    }
    // 순열 돌려서 중앙 선 지나게 배치하는 방법 다 돌려봄
    private static void permutation(int[] line, boolean[] visited, int depth) {
        if(depth == N) {    // 순열 완성하면 중앙 지나는 선 몇개인지 확인
            RESULT = Math.max(RESULT, checkLine(line));
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(depth == 0) line[depth] = dogs[i];   // 처음이면 그냥 값 넣기
                else line[depth] = line[depth-1] + dogs[i]; // 누적된 값을 확인해야하니까 이전 값에서 이번 값 더하기
                permutation(line, visited, depth+1);
                visited[i] = false;
            }
        }
    }
    private static int checkLine(int[] line) {
        int cnt = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(line[i] + 50 == line[j]) {   // 돌면서 만약에 50 더한게 있으면, 중앙 지나는 선이 있다는 뜻
                    cnt++;
                }
            }
        }
        return cnt;
    }
}