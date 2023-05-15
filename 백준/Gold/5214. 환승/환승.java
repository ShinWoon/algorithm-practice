import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int N, K, M, minVisitedStation;
    static Map<Integer, List<Integer>> relationShip;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);
        M = Integer.parseInt(tmp[2]);

        relationShip = new HashMap<>();
        int idx = N+1;
        for(int i=1; i<=N; i++) relationShip.put(i, new ArrayList<>());
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            relationShip.put(idx, new ArrayList<>());
            for(int j=0; j<tmp.length; j++) {
                int num = Integer.parseInt(tmp[j]);
                relationShip.get(num).add(idx);
                relationShip.get(idx).add(num);
            }
            idx++;
        }
        visited = new boolean[idx];
        solve();
        if(minVisitedStation == (int)1e9) System.out.println(-1);
        else System.out.println(minVisitedStation);
    }
    private static void solve() {
        minVisitedStation = (int)1e9;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{1,1});  //[start, visitedStationCnt]
        visited[1] = true;

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int currentStation = info[0];
            int visitedCnt = info[1];

            if(currentStation == N) {
                minVisitedStation = visitedCnt;
                return;
            }

            for(int i=0;i < relationShip.get(currentStation).size(); i++) {
                Integer nextStation = relationShip.get(currentStation).get(i);
                if(!visited[nextStation]){
                    if(nextStation > N) {
                        visited[nextStation] = true;
                        q.add(new Integer[]{nextStation, visitedCnt});
                    } else {
                        visited[nextStation] = true;
                        q.add(new Integer[]{nextStation, visitedCnt + 1});
                    }
                }
            }
        }

    }
}