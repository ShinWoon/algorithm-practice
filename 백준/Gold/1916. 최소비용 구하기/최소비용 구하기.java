import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, start, end;
    private static int[] totalCostMap;

    private static class Node implements Comparable<Node> {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    private static List<List<Node>> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodeList.get(s).add(new Node(e, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        totalCostMap = new int[N + 1];
        for (int i = 1; i <= N ; i++) {
            totalCostMap[i] = Integer.MAX_VALUE;
        }
        solve();
        System.out.println(totalCostMap[end]);
    }

    private static void solve() {
        PriorityQueue<Node> q = new PriorityQueue();
        q.add(new Node(start, 0));
        boolean[] visited = new boolean[N+1];
        totalCostMap[start] = 0;

        while (!q.isEmpty()) {
            Node currentInfo = q.poll();
            int currentSpot = currentInfo.end;
            int currentTotalCost = currentInfo.cost;

            if(visited[currentSpot]) continue;

            visited[currentSpot] = true;
            for (int i = 0; i < nodeList.get(currentSpot).size(); i++) {
                int nextSpot = nodeList.get(currentSpot).get(i).end;
                int newCost = currentTotalCost + nodeList.get(currentSpot).get(i).cost;
                if (!visited[nextSpot] && newCost < totalCostMap[nextSpot]) {
                    totalCostMap[nextSpot] = newCost;
                    q.add(new Node(nextSpot, newCost));
                }
            }
        }
    }
}