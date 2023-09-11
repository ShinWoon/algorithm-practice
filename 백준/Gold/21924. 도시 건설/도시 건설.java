import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Info {
        int start;
        int end;
        int cost;
        public Info(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    private static int N, M, checkConnection;
    private static long totalCost, leastCost;
    private static int[] parents;
    private static Queue<Info> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        totalCost = 0;
        parents = new int[N+1];
        for(int i=1; i<=N; i++) parents[i] = i;

        q = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.cost - o2.cost;
            }
        });
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            int cost = Integer.parseInt(tmp[2]);
            q.add(new Info(start, end, cost));
            q.add(new Info(end, start, cost));
            totalCost += cost;
        }
        solve();
        if(checkConnection == N-1) System.out.println(totalCost - leastCost);
        else System.out.println(-1);
    }

    private static void solve() {
        leastCost = 0;
        checkConnection = 0;
        while(!q.isEmpty()) {
            Info info = q.poll();
            int rx = find(info.end);
            int ry = find(info.start);

            if(!isSameParent(rx, ry)) {
//                System.out.println(info.cost);
                leastCost += info.cost;
                union(info.start, info.end);
                checkConnection++;
            }
        }
    }

    private static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    private static void union(int x, int y) {
        int ux = find(x);
        int uy = find(y);

        if(ux < uy) parents[uy] = ux;
        else parents[ux] = uy;
    }

    private static boolean isSameParent(int x, int y) {
        if(x == y) return true;
        return false;
    }
}