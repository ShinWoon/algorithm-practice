import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info {
        int to;
        int time;
        public Info(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
    static int N, M, X, maxResult;
    static Map<Integer, List<Info>> path1, path2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        X = Integer.parseInt(tmp[2]);

        path1 = new HashMap<>();
        path2 = new HashMap<>();
        for(int i=1; i<=N; i++) {
            path1.put(i, new ArrayList<>());
            path2.put(i, new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            int time = Integer.parseInt(tmp[2]);
            path1.get(from).add(new Info(to, time));
            path2.get(to).add(new Info(from, time));
        }

        solve();
        System.out.println(maxResult);
    }

    public static void solve() {
        int[] time1 = new int[N+1];
        int[] time2 = new int[N+1];
        int[] result = new int[N+1];

        // 가는 길의 최단 거리 구하기
        for(int i=1; i<=N; i++) {
            time1[i] = (int)1e9;
            time2[i] = (int)1e9;
        }

        time1 = dijkstra(X, path1, time1);
        time2 = dijkstra(X, path2, time2);

//        for(int i=1; i<=N; i++) System.out.print(time1[i] + " ");
//        System.out.println();
//        for(int i=1; i<=N; i++) System.out.print(time2[i] + " ");
//        System.out.println();

        // 최단 거리들이 다 구해졌으면 학생마다 X를 거쳐서 돌아오는 길의 최단 시간을 구하자
        // 가장 오래 걸린 학생의 시간 가지고 오기
        maxResult = 0;
        for(int i=1; i<=N; i++){
            if(i == X) result[i] = 0;
            else result[i] = time1[i] + time2[i];
            maxResult = Math.max(maxResult, result[i]);
        }

    }

    public static int[] dijkstra(int start, Map<Integer, List<Info>> path, int[] time) {
        Queue<Info> q = new LinkedList<>();
        time[start] = 0;
        q.add(new Info(start,time[start]));

        while(!q.isEmpty()) {
            Info current = q.poll();
            int currentNum = current.to;
            int currentTime = current.time;

            if(time[currentNum] < currentTime) continue;

            for(int i=0; i<path.get(currentNum).size(); i++) {
                int nextSpot = path.get(currentNum).get(i).to;
                int distance = path.get(currentNum).get(i).time;
                if(time[nextSpot] > currentTime + distance){
                    time[nextSpot] = currentTime + distance;
                    q.add(new Info(nextSpot, time[nextSpot]));
                }
            }
        }
        return time;
    }
}