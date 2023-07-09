import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[] friends;
    private static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new int[N+1][N+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                if(i==j) continue;
                map[i][j] = (int)1e9;
            }
        }
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            int time = Integer.parseInt(tmp[2]);
            map[from][to] = time;
        }

        K = Integer.parseInt(br.readLine());
        friends = new int[K];
        tmp = br.readLine().split(" ");
        for(int i=0; i<K; i++) friends[i] = Integer.parseInt(tmp[i]);

        solve();
    }

    private static void solve() {

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

//        for(int i=1; i<=N; i++) {
//            for(int j=1; j<=N; j++) System.out.print(map[i][j] +" ");
//            System.out.println();
//        }

        // 왕복 시간
        // 친구들마다 방문 시간 중 최댓값 구하기
        int[] round = new int[N+1];
        for(int k=0; k<K; k++) {
            int start = friends[k];
            for(int city=1; city<=N; city++) {
                round[city] = Math.max(round[city], map[start][city] + map[city][start]);
            }
        }

//        for(int i=1; i<=N; i++) System.out.print(round[i] + " ");

        // 왕복 시간 중 최소가 되는 도시 구하기
        List<Integer> cityList = new ArrayList<>();
        int leastTime = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            if(leastTime < round[i]) continue;
            if(leastTime > round[i]) {
                leastTime = round[i];
                cityList = new ArrayList<>();
            }
            cityList.add(i);
        }

        // 도시 오름차순 출력
        Collections.sort(cityList);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<cityList.size(); i++) sb.append(cityList.get(i) +" ");
        System.out.println(sb);
    }
}