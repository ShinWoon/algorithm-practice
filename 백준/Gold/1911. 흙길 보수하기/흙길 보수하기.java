import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Pond{
        int start;
        int end;

        public Pond(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private static int N, L;
    private static List<Pond> pondList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        pondList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pondList.add(new Pond(start, end));
        }

        Collections.sort(pondList, (o1,o2) -> o1.start - o2.start); // 겹치지 않을거니까 앞 기준으로만 정렬

        System.out.println(solution());

    }
    private static int solution() {
        int cntPond = 0;
        int pondStart = 0;
        int pondEnd = 0;
        for(Pond thisPond : pondList) {
            int plankCnt;
             if(pondEnd < thisPond.start) { // 이전에 덮은 널빤지가 이 웅덩이 일부 안 가리면
                 if((thisPond.end - thisPond.start)%L == 0) {   // 널빤지가 딱 맞게 들어가면
                     plankCnt = (thisPond.end - thisPond.start)/L;
                 } else {   // 딱 안맞게 들어가면 하나 더 추가
                     plankCnt = (thisPond.end - thisPond.start)/L + 1;
                 }
                 pondEnd = thisPond.start + plankCnt * L;
             } else if (pondEnd < thisPond.end) {   // 이전에 덮은 널빤지가 이 웅덩이 일부 가리면
                 pondStart = pondEnd;
                 if((thisPond.end - pondStart)%L == 0) {
                     plankCnt = (thisPond.end - pondStart)/L;
                 } else {
                     plankCnt = (thisPond.end - pondStart)/L + 1;
                 }
                 pondEnd = pondStart + plankCnt * L;
             } else {   // 이 웅덩이도 이미 덮혀있음
                 continue;
             }
            cntPond += plankCnt;
        }
        return cntPond;
    }
}