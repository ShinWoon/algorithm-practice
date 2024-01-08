import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, T;
    private static List<Long> busList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        busList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int interval = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++) {
                busList.add((long) (start + interval*j));
            }
        }
        Collections.sort(busList);
        long result = solve();
        System.out.println(result);
    }

    private static long solve() {
        int start = 0;
        int end = busList.size();
        int middle = 0;
        if(busList.get(end-1) - T < 0) return -1;
        if(busList.get(0) >= T) return busList.get(0) - T;

        while (start<end) {
            middle = (start+end)/2;
            if(busList.get(middle) < T) {
                start = middle+1;
            } else if(busList.get(middle) == T) {
                return 0;
            }else{
                end = middle;
            }
        }
        return (busList.get(end)- T);
    }
}