import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static String result;
    private static int N;
    private static long[][] point;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        point = new long[N+1][2];

        for(int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            point[i][0] = x;
            point[i][1] = y;
        }

        point[N][0] = point[0][0];
        point[N][1] = point[0][1];

        solve();
        System.out.println(result);
    }

    private static void solve() {
        // 점들로 면적 구하는 공식
        // 1/2 * |(x1*y2 + x2*y3 + x3*y1) - (y1*x2 + y2*x3 + y3*x1)|

//        // 고정 값
//        int x = point[0][0];
//        int y = point[0][1];
//        long triSum = 0;
//        for(int i=1; i<N-1; i++) {
//            int x2 = point[i][0];
//            int y2 = point[i][1];
//            int x3 = point[i+1][0];
//            int y3 = point[i+1][1];
//            triSum += ((x*y2 + x2*y3 + x3*y) - (y*x2 + y2*x3 + y3*x)) * 0.5;
//        }

        long areaA = 0;
        long areaB = 0;

        for(int i=0; i<N; i++) {
            areaA += point[i][0] * point[i+1][1];
            areaB += point[i+1][0] * point[i][1];
        }

        result = String.format("%.1f", Math.abs(areaA-areaB)/2.0);
    }
}