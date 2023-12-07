import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Time {
        int s;
        int t;

        public Time(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }
    private static int N;
    private static List<Time> timeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        timeList = new ArrayList<>();
        String[] tmp;
        for (int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]);
            int t = Integer.parseInt(tmp[1]);
            timeList.add(new Time(s,t));
        }

        timeList.sort(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if(o1.s == o2.s) return o1.t - o2.t;
                return o1.s - o2.s;
            }
        });

        int result = solve();
        System.out.println(result);
    }

    private static int solve() {
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1- o2;
            }
        });
        q.add(timeList.get(0).t);
        for (int i=1; i<timeList.size(); i++) {
            Time time = timeList.get(i);
            if(q.peek() <= time.s) {    // 가능하면 이전꺼 빼고 새로 값 갱신
                q.poll();
            }
            q.add(time.t);
        }
        return q.size();
    }

}