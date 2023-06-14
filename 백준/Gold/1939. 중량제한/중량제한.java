import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Info {
        int to;
        int weight;
        public Info(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    private static int N, M, start, end;
    private static int[] weightMap;
    private static List<Info> relation[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        relation = new ArrayList[N+1];
        for(int i=1;i<=N; i++) relation[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);

            relation[from].add(new Info(to, weight));
            relation[to].add(new Info(from, weight));
        }

        tmp = br.readLine().split(" ");
        start = Integer.parseInt(tmp[0]);
        end = Integer.parseInt(tmp[1]);

        weightMap = new int[N+1];
        solve();
        System.out.println(weightMap[end]);
    }

    private static void solve() {
        int currentWeight = Integer.MAX_VALUE;
        Queue<Info> q = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if (o1.weight < o2.weight) {
                    return 1;
                }
                return -1;
            }
        });
        q.add(new Info(start, currentWeight));
        weightMap[start] = currentWeight;

        while (!q.isEmpty()) {
            Info information = q.poll();
            int currentNode = information.to;
            currentWeight = information.weight;

            if(weightMap[currentNode] > currentWeight) continue;
            for(int i=0; i<relation[currentNode].size(); i++) {
                Info info = relation[currentNode].get(i);
                int nextNode = info.to;
                int nextWeight = info.weight;
                nextWeight = Math.min(currentWeight, nextWeight);
                if(weightMap[nextNode] < nextWeight) {
                    weightMap[nextNode] = nextWeight;
                    q.add(new Info(nextNode, nextWeight));
                }
            }
        }
    }
}