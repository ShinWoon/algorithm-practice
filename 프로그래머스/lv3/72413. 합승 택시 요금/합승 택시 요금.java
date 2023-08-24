import java.util.*;

class Solution {
    private static class Nodes {
        int to;
        int cost;

        public Nodes(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    private static Map<Integer, List<Nodes>> routeMap;
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
//        int answer = 0;

        routeMap = new HashMap<>();
        for (int i = 1; i <= n; i++) routeMap.put(i, new ArrayList<>());
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];

            routeMap.get(from).add(new Nodes(to, cost));
            routeMap.get(to).add(new Nodes(from, cost));
        }

//        dijkstra();

        int[] costAArr = new int[n + 1];
        int[] costBArr = new int[n + 1];
        int[] costSArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            costAArr[i] = Integer.MAX_VALUE;
            costBArr[i] = Integer.MAX_VALUE;
            costSArr[i] = Integer.MAX_VALUE;
        }

        // A, B, S에서 시작하는 최단 거리
        costAArr = dijkstra(a, costAArr);
//        for(int i=1; i<=n; i++) System.out.print(costAArr[i] + " ");
        costBArr = dijkstra(b, costBArr);
        costSArr = dijkstra(s, costSArr);

//        int minCost = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            int totalCost = costAArr[i] + costBArr[i] + costSArr[i];
//            minCost = Math.min(minCost, totalCost);
            answer = Math.min(answer, totalCost);
        }
//        answer = minCost;
        return answer;
    }

    private static int[] dijkstra(int start, int[] costArr) {
//        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
//            @Override
//            public int compare(Integer[] o1, Integer[] o2) {
//                return o1[1] - o2[1];
//            }
//        });
//        costArr[start] = 0;
//        q.add(new Integer[]{start, costArr[start]});

        Queue<Nodes> q = new PriorityQueue<>(new Comparator<Nodes>() {
            @Override
            public int compare(Nodes o1, Nodes o2) {
                return o1.cost - o2.cost;
            }
        });
        costArr[start] = 0;
        q.add(new Nodes(start, costArr[start]));

        while (!q.isEmpty()) {
//            Integer[] info = q.poll();
//            int current = info[0];
//            int currentCost = info[1];
            Nodes nodes = q.poll();
            int current = nodes.to;
            int currentCost = nodes.cost;

            for (int i = 0; i < routeMap.get(current).size(); i++) {
                int next = routeMap.get(current).get(i).to;
                int nextCost = routeMap.get(current).get(i).cost;

                if (costArr[next] > currentCost + nextCost) {
                    costArr[next] = currentCost + nextCost;
//                    q.add(new Integer[]{next, costArr[next]});
                    q.add(new Nodes(next, costArr[next]));
                }
            }
        }
        return costArr;
    }
}