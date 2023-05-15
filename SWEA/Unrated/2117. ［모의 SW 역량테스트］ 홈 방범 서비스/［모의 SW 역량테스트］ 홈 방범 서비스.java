import java.util.Scanner;

public class Solution {
    static int N, M, maxHouseCnt;
    static int[][] map;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
//            System.out.println(N + " " + M);
            solve();
            System.out.println("#" + test_case + " " + maxHouseCnt);
        }
    }

    private static void solve() {
        maxHouseCnt = 0;

        // k = 1 ~ (N+1)까지 돌아야 위에 끝에 있는 애들도 다 볼 수 있음 -> tc 10에서 유추...
        for(int k=1; k<=N+1; k++) {
            // 조회는 도시를 벗어난 경우도 괜찮으니까 다 돌자
            int cost = k * k + (k-1) * (k-1);
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    // 지도에서 (j,i)를 기점으로 조회하기
                    findServicePossible(k, i , j, cost);
                }
            }
        }
    }

    private static void findServicePossible(int k, int y, int x, int cost) {
        int cnt = 0;
        for(int i=y-(k-1); i<=y+(k-1); i++) {
            for(int j=x-(k-1); j<=x+(k-1); j++) {
                if(i<0 || i>=N || j<0 || j>=N) continue;
                if(Math.abs(y-i) + Math.abs(x-j) < k) {
                    // 범위 내에 속해 있는 경우, 값이 1이면 집
                    if(map[i][j] == 1) cnt++;
                }
            }
        }
        // 이익 = (집 수 * M) - 운영비용 -> (집 수 * M) >= 운영비용 만족하면 됨. 0이라도 손해만 안 보면 되니까.
        if(cnt*M >= cost) {
            maxHouseCnt = Math.max(maxHouseCnt, cnt);
        }
    }
}