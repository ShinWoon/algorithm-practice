import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int N;
    private static List<Integer> posNumList, negNumList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        posNumList = new ArrayList<>();
        negNumList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num <= 0) negNumList.add(num);
            else posNumList.add(num);
        }

        posNumList.sort(Collections.reverseOrder());    // 양수는 큰 수 부터
        Collections.sort(negNumList);   // 음수는 작은 수 부터
        System.out.println(solve());
    }
    private static int solve() {
        int cnt = 0;
        for(int i=0; i<posNumList.size(); i++) {
            if(i==posNumList.size()-1) {
                cnt += posNumList.get(i);
            } else {
                if(posNumList.get(i) + posNumList.get(i+1) >= posNumList.get(i) * posNumList.get(i+1)) {
                    cnt += posNumList.get(i);
                } else {
                    cnt += posNumList.get(i) * posNumList.get(i+1);
                    i++;
                }
            }
        }
        for(int i=0; i< negNumList.size(); i++) {
            if(i==negNumList.size()-1) {
                cnt += negNumList.get(i);
            } else {
                if(negNumList.get(i) + negNumList.get(i+1) >= negNumList.get(i) * negNumList.get(i+1)) {
                    cnt += negNumList.get(i);
                } else {
                    cnt += negNumList.get(i) * negNumList.get(i+1);
                    i++;
                }
            }
        }
        return cnt;
    }
}