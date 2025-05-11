import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, result;
    private static int[][] map;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        result = Integer.MAX_VALUE;
        solve(0,0);
        System.out.println(result);
    }
    private static void solve(int idx, int cnt) {
        if(idx == N) {
            // 두 팀 인원 수는 같지 않아도 되지만, 무조건 한팀에 한명은 있어야 함
            if(cnt >= 1 && cnt < N) {
                // 두 팀 능력치 차이 계산
                calcPower();
            }
            return;
        }

        // idx가 스타트 팀인 경우
        visited[idx] = true;
        solve(idx + 1, cnt + 1);
        // idx가 링크 팀인 경우
        visited[idx] = false;
        solve(idx + 1, cnt);
    }
    private static void calcPower() {
        int startPow = 0;
        int linkPow = 0;

        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                if(visited[i] && visited[j]) {
                    // 스타트 팀
                    startPow += map[i][j] + map[j][i];
                } else if (!visited[i] && !visited[j]) {
                    // 링크 팀
                    linkPow += map[i][j] + map[j][i];
                }
            }
        }
        int diffPow = Math.abs(startPow - linkPow);
        result = Math.min(result, diffPow);
    }
}
