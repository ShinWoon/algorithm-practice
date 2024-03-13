import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N;
    private static Point[] points;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        points = new Point[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            points[i] = new Point(a,b);
        }

        // x 좌료가 빨리 오는 순으로 정렬
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
        });


        long start = points[0].x;
        long end = points[0].y;
        long totalLen = Math.abs(points[0].y - points[0].x);
        for(int i=1; i<N; i++) {
            Point currentPoint = points[i];
            if(currentPoint.x >= start && currentPoint.x <= end) {
                if(currentPoint.y <= end) {
                    continue;
                } else {
                    totalLen += Math.abs(currentPoint.y - end);
                    end = currentPoint.y;
                }
            } else {
                start = currentPoint.x;
                end = currentPoint.y;
                totalLen += Math.abs(currentPoint.y - currentPoint.x);
            }
        }

        System.out.println(totalLen);
    }
}