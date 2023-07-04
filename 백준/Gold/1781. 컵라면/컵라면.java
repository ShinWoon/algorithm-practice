import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Info {
        int deadLine;
        int cupNoodle;
        public Info(int deadLine, int cupNoodle) {
            this.deadLine = deadLine;
            this.cupNoodle = cupNoodle;
        }
        @Override
        public String toString() {
            return "Info{" +
                    "deadLine=" + deadLine +
                    ", cupNoodle=" + cupNoodle +
                    '}';
        }
    }
    private static int N, maxDDate;
    private static List<Info> infoList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        maxDDate = 0;
        infoList = new ArrayList<>();

        String[] tmp;
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int dDay = Integer.parseInt(tmp[0]);
            int noodle = Integer.parseInt(tmp[1]);
            infoList.add(new Info(dDay, noodle));
            maxDDate = Math.max(maxDDate, dDay);
        }

        infoList.sort(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.deadLine - o1.deadLine;
            }
        });
        // deadline이라면,,,
        // 그 deadline 도달하지 않은 애를 가져야 함,.,,
        // priorityQ를 어디다가 쓸지,,.

//        System.out.println(infoQ.toString());
        solve();
    }

    private static void solve() {

        long result = 0;
        int idx = 0;
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int date=maxDDate; date>=1; date--) {
            while (true) {
                if(idx >= N) break;
                if(infoList.get(idx).deadLine >= date) {
                    q.add(infoList.get(idx).cupNoodle);
                } else if (infoList.get(idx).deadLine < date) break;

                idx++;
            }
            if(!q.isEmpty()) result += q.poll();
        }

//        System.out.println(infoList.toString());
        System.out.println(result);
    }
}