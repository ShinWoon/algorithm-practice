import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, answer;
    private static boolean[] visited;
    private static Map<Integer, List<Integer>> fMap, eMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        fMap = new HashMap<>(); // 친구 사이
        eMap = new HashMap<>(); // 원수 사이
        for (int i = 1; i <= N; i++) {
            fMap.put(i, new ArrayList<>());
            eMap.put(i, new ArrayList<>());
        }

        String[] tmp;
        for (int i = 0; i < M; i++) {
            tmp = br.readLine().split(" ");
            String type = tmp[0];
            int p = Integer.parseInt(tmp[1]);
            int q = Integer.parseInt(tmp[2]);

            if (type.equals("E")) {
                eMap.get(p).add(q);
                eMap.get(q).add(p);
            } else {
                fMap.get(p).add(q);
                fMap.get(q).add(p);
            }
        }

        answer = 0;
        solve();
        System.out.println(answer);
    }

    private static void solve() {
        // 원수의 원수는 내 친구
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                findTeammate(i, i, 0);
            }
        }

        // 내 친구
        visited = new boolean[N + 1];
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
//                System.out.println(i);
                myTeammate(i);
                answer++;
            }
        }
    }

    // 원수의 원수 찾기
    private static void findTeammate(int start, int current, int level) {
        if (level == 2) { // 원수 -> 원수 == 내 친구 == level 2
            fMap.get(start).add(current);   // 원수의 원수는 내 친구니까
            return;
        }

        for (int i = 0; i < eMap.get(current).size(); i++) {
            int next = eMap.get(current).get(i);
            if (!visited[next]) {
                visited[next] = true;
                findTeammate(start, next, level + 1);
                visited[next] = false;
            }
        }
    }

    // 내 친구들
    private static void myTeammate(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i=0; i<fMap.get(current).size(); i++) {
                int next = fMap.get(current).get(i);
//                System.out.println("next : " + next);
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}