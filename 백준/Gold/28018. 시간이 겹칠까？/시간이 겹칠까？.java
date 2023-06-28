import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, Q;
    private static int[] timeArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        timeArr = new int[1_000_002];
        int maxEndTime = 0;
        N = Integer.parseInt(br.readLine());
        String[] tmp;
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            timeArr[start] += 1;
            timeArr[end+1] -= 1;
            maxEndTime = Math.max(maxEndTime, end);
        }

        for(int i=0; i<=maxEndTime; i++) {
            timeArr[i+1] += timeArr[i];
        }

//        for(int i=0; i<= maxEndTime; i++) System.out.print(timeArr[i] + " ");
//        System.out.println();
        Q = Integer.parseInt(br.readLine());
        tmp = br.readLine().split(" ");
        for(String time : tmp) {
            int thisTime = Integer.parseInt(time);
            System.out.println(timeArr[thisTime]);
        }
    }
}