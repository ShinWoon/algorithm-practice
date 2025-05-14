import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Lecture implements Comparable<Lecture> {
        int lectureNum;
        int startTime;
        int endTime;

        Lecture(int lectureNum, int startTime, int endTime) {
            this.lectureNum = lectureNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.startTime < o.startTime) {
                return -1;
            } else if(this.startTime == o.startTime) {
                if(this.endTime < o.endTime) {
                    return -1;
                } else if(this.endTime > o.endTime) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        }
    }
    private static int N;
    private static List<Lecture> lectureList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lectureList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int lectureNum = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            lectureList.add(new Lecture(lectureNum, startTime, endTime));
        }
        Collections.sort(lectureList);

        PriorityQueue<Integer> classQ = new PriorityQueue<>();
        int maxClass = 1;
        for(int i=0; i<N; i++) {
            int startT = lectureList.get(i).startTime;
            int endT = lectureList.get(i).endTime;
            if(classQ.isEmpty()) {
                classQ.add(endT);
            } else {
                if(startT >= classQ.peek()) {
                    classQ.poll();
                    classQ.add(endT);
                } else {
                    classQ.add(endT);
                }
            }
            maxClass = Math.max(maxClass, classQ.size());
        }
        System.out.println(maxClass);
    }
}
