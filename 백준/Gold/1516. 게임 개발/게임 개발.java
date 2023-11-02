import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] buildTime, parentCnt, result;
    private static Map<Integer, List<Integer>> childInfoMap;
    private static Queue<Integer> q;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        buildTime = new int[N + 1];
        result = new int[N + 1];
        parentCnt = new int[N+1];
        childInfoMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            childInfoMap.put(i, new ArrayList<>());
        }
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            buildTime[i + 1] = Integer.parseInt(tmp[0]);
            for (int j = 1; j < tmp.length - 1; j++) {
                int buildNum = Integer.parseInt(tmp[j]);
                childInfoMap.get(buildNum).add(i + 1);
                parentCnt[i+1] = tmp.length - 2;
            }
            if (parentCnt[i+1] == 0) {
                q.add(i+1);
                result[i+1] = buildTime[i+1];
            }
        }

        solve();
        printResult();
        System.out.println(sb);
    }
    private static void solve() {
        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i=0; i<childInfoMap.get(current).size(); i++) {
                int next = childInfoMap.get(current).get(i);
                result[next] = Math.max(result[next],buildTime[next] + result[current]);
                parentCnt[next] -= 1;
                if(parentCnt[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
    private static void printResult() {
        for(int i=1; i<=N; i++) {
            sb.append(result[i]);
            if(i == N) continue;
            sb.append("\n");
        }
    }
}