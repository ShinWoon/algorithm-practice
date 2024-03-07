import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] power;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        power = new int[N];
        for(int i=0; i<N; i++) power[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = N-1;
        int result = 0;
        while (start <= end) {
            int teamPower;
            teamPower= (end - start - 1) * Math.min(power[start], power[end]);
            result = Math.max(result, teamPower);
//            System.out.println(start + " " + end + " " + teamPower);
            if(power[start] < power[end]) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(result);
    }
}