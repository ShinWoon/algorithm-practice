import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Node {
        int house;
        int cost;

        public Node(int house, int cost) {
            this.house = house;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "house=" + house +
                    ", cost=" + cost +
                    '}';
        }
    }
    private static int N, M;
    private static int[] friendHouse, possibleHouse;
    private static List<Node>[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        friendHouse = new int[3];
        for(int i=0; i<3; i++) friendHouse[i] = Integer.parseInt(st.nextToken());

        possibleHouse = new int[N-3];
        int idx=0;
        for (int i=1; i<=N; i++) {
            if(friendHouse[0] != i && friendHouse[1] != i && friendHouse[2] != i) {
                possibleHouse[idx++] = i;
            }
        }

        M = Integer.parseInt(br.readLine());
        map = new ArrayList[N+1];
        for(int i=1; i<=N; i++) map[i] = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map[D].add(new Node(E, L));
            map[E].add(new Node(D, L));
        }

        List<int[]> resultList = new ArrayList<>();
        for(int friendHome: friendHouse) {
            resultList.add(solution(friendHome));
        }

        // 집 최단 거리중 최장거리 선택
        int maxLen = Integer.MIN_VALUE;
        int chooseHouse = 0;
        for(int i=1; i<=N; i++) {
            if(i == friendHouse[0] || i == friendHouse[1] || i == friendHouse[2]) continue;
            int house1 = resultList.get(0)[i];
            int house2 = resultList.get(1)[i];
            int house3 = resultList.get(2)[i];
            int minLen = Math.min(house1,Math.min(house2, house3));
            if(minLen > maxLen) {
                maxLen = minLen;
                chooseHouse = i;
            }
        }
        System.out.println(chooseHouse);
    }
    private static int[] solution(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node thisNode = q.poll();

            if(dist[thisNode.house] < thisNode.cost) continue;

            for(Node next: map[thisNode.house]) {
                if(dist[next.house] > thisNode.cost + next.cost) {
                    dist[next.house] = thisNode.cost + next.cost;
                    q.add(new Node(next.house, dist[next.house]));
                }
            }
        }
//        System.out.println(Arrays.toString(dist));
        return dist;
    }
}