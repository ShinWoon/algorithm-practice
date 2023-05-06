import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, minScore;
    static Map<Integer, List<Integer>> relationShip;
    static List<Integer> candidates;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        relationShip = new HashMap<>();
        for(int i=1; i<=N; i++) relationShip.put(i, new ArrayList<>());
        while(true) {
            String[] tmp = br.readLine().split(" ");
            int friend1 = Integer.parseInt(tmp[0]);
            int friend2 = Integer.parseInt(tmp[1]);
            if(friend1 == - 1) break;
            relationShip.get(friend1).add(friend2);
            relationShip.get(friend2).add(friend1);
        }

        solve();
        System.out.println(minScore + " " + candidates.size());
        for(int candidate : candidates) System.out.print(candidate + " ");
    }

    private static void solve() {
        minScore = (int)1e9;

        for(int i=1; i<=N; i++) {   // 각 후보마다 점수 구하기
            int score = bfs(i);
            if(score < minScore) {  // 최소 값을 가지는 자가 후보가 됨
                candidates = new ArrayList<>();
                candidates.add(i);
                minScore = score;
            } else if (score == minScore) candidates.add(i);    // 최소 값과 같은 점수를 가지면 후보
        }
    }

    private static int bfs(int start) {
        int score = 0, maxScore = 0;
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{start, score});

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            start = info[0];
            score = info[1];
            maxScore = Math.max(score, maxScore);

            for(int i=0; i<relationShip.get(start).size(); i++) {
                int friend = relationShip.get(start).get(i);
                if(!visited[friend]) {
                    visited[friend] = true;
                    q.add(new Integer[]{friend, score+1});
                }
            }
        }
        return maxScore;
    }
}