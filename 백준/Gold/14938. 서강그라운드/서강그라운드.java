import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int to;
        int l;
        public Node(int to, int l) {
            this.to = to;
            this.l = l;
        }
    }
    static int N, M, R, maxItemCnt;
    static int[] items;
    static int[] distanceArr;
    static Map<Integer, List<Node>> disInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        R = Integer.parseInt(tmp[2]);

        items = new int[N+1];
        tmp = br.readLine().split(" ");
        for(int i=0; i<N ; i++) items[i+1] = Integer.parseInt(tmp[i]);

        disInfo = new HashMap<>();
        for(int i=1; i<=N; i++) disInfo.put(i, new ArrayList<>());
        for(int i=0; i<R; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            int len = Integer.parseInt(tmp[2]);
            disInfo.get(from).add(new Node(to, len));
            disInfo.get(to).add(new Node(from, len));
        }

        solve();
        System.out.println(maxItemCnt);
    }

    private static void solve() {
        maxItemCnt = 0;
        distanceArr = new int[N+1];
        // 어디에 내려야 아이템 가장 많이 먹을 수 있는지 확인하기 위해 다 시작해보기
        for(int i=1; i<=N; i++) {
            setDistanceArr();   // 거리 저장을 위해 갱신
            findWay(i);         // 최단 거리 구하기
            getMaxItemNum();    // 이번에 가장 많이 먹을 수 있는 아이템 수 구하고 최고 많이 먹는 아이템 수 갱신
        }

    }

    private static void findWay(int start) {
        Queue<Integer[]> q = new LinkedList<>();
        distanceArr[start] = 0;
        q.add(new Integer[]{start, distanceArr[start]});

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int now = info[0];
            int nowDis = info[1];

            if(distanceArr[now] < nowDis) continue;
            for(int i=0; i<disInfo.get(now).size(); i++) {
                int next = disInfo.get(now).get(i).to;
                int nextDis = disInfo.get(now).get(i).l;
                if(distanceArr[next] > nowDis + nextDis) {
                    distanceArr[next] = nowDis + nextDis;
                    q.add(new Integer[]{next, distanceArr[next]});
                }
            }

        }
    }

    private static void setDistanceArr() {
        for(int i=1; i<=N; i++) distanceArr[i] = (int)1e9;
    }

    private static void getMaxItemNum() {
        int itemCnt = 0;
        for(int i=1; i<=N; i++) {
            if(distanceArr[i] <= M) itemCnt += items[i];
        }
        maxItemCnt = Math.max(maxItemCnt, itemCnt);
    }
}