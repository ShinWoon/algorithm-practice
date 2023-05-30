import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class BusInfo {
        int end;
        int cost;
        public BusInfo(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    private static int N, M, startingP, arrivalP, resultCost;   // N = 도시 수, M = 버스 수
    private static int[] costList;
    private static Map<Integer, List<BusInfo>> relation;
    private static Stack<Integer> resultStack;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        relation = new HashMap<>();
        costList = new int[N+1];
        String[] tmp;
        // 값 초기화
        for(int i=1; i<=N; i++) {
            relation.put(i, new ArrayList<>());
            costList[i] = (int)1e9;
        }

        // 버스 정보 저장
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            int cost = Integer.parseInt(tmp[2]);
            relation.get(start).add(new BusInfo(end, cost));
        }

        // 시작점, 도착점 저장
        tmp = br.readLine().split(" ");
        startingP = Integer.parseInt(tmp[0]);
        arrivalP = Integer.parseInt(tmp[1]);

        solve();
        System.out.println(resultCost);
        // 방문 기록 스택에서 빼기
        System.out.println(resultStack.size());
        while(!resultStack.isEmpty()) System.out.print(resultStack.pop() + " ");
    }

    private static void solve() {
        resultCost = 0;
        resultStack = new Stack<>();
        dijkstra();
    }

    private static void dijkstra() {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{startingP, 0}); // [node 번호, 지금까지의 비용 값]
        int[] visited = new int[N+1];
        costList[startingP] = 0;
        visited[startingP] = 0;

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            int currentNode = info[0];
            int currentCost = info[1];
//            System.out.println("current cost : " + currentCost);
            if(currentNode == arrivalP) {
                resultCost = costList[arrivalP];
            }
            if(costList[currentNode] < currentCost) continue;

            for(int i=0; i<relation.get(currentNode).size(); i++) {
                BusInfo bus = relation.get(currentNode).get(i);
                int nextNode = bus.end;
                int nextCost = bus.cost;

                if(costList[nextNode] > currentCost + nextCost) {
                    costList[nextNode] = currentCost + nextCost;
                    visited[nextNode] = currentNode;
//                    System.out.println("current : "  + currentNode + " next node : " + nextNode + " next node cost : " + costList[nextNode]);
                    q.add(new Integer[]{nextNode, costList[nextNode]});
                }
            }
        }

        // 방문 한 곳들 스택에 저장
        int idx = arrivalP;
        resultStack.add(arrivalP);
        while(true) {
            resultStack.add(visited[idx]);
            idx = visited[idx];
            if(idx == startingP) break;
        }
    }
}