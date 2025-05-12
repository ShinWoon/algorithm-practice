import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<Integer> crane, boxWeight;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        crane = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }
        // 크레인 무게 오름차순 정렬
        Collections.sort(crane);
        Collections.reverse(crane);

        M = Integer.parseInt(br.readLine());
        boxWeight = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            boxWeight.add(Integer.parseInt(st.nextToken()));
        }
        // 박스 무게 오름차순 정렬
        Collections.sort(boxWeight);
        Collections.reverse(boxWeight);

        if(boxWeight.get(0) > crane.get(0)) {
            System.out.println("-1");
            return;
        }

        int boxIdx = 0;
        int craneIdx= 0;
        int day = 0;
        while (boxWeight.size() > 0) {
            boxIdx = 0;
            craneIdx = 0;
            while (true) {
                if(craneIdx >= N || boxIdx >= boxWeight.size()) break;
                if(crane.get(craneIdx) >= boxWeight.get(boxIdx)){
                    craneIdx++;
                    boxWeight.remove(boxIdx);
                } else {
                    boxIdx++;
                }
            }
            day += 1;
        }
        
        System.out.println(day);
    }

}
