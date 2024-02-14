import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, L;
    private static int[] guns;
    private static int[][] animals;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        guns = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) guns[i] = Integer.parseInt(st.nextToken());
        animals = new int[M][2];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(guns);
        Arrays.sort(animals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int result = solution();
        System.out.println(result);
    }
    private static int solution() {
        // 동물의 위치에서 사대까지 거리를 이분 탐색
        int cnt = 0;
        for(int i=0; i<M; i++) {
            int a = animals[i][0];
            int b = animals[i][1];
            int start = 0;
            int end = N;
            while (start < end) {
                int middle = (start + end)/2;
                int distance = Math.abs(guns[middle] - a) + b;
                if(distance<=L) {
                    cnt++;
                    break;
                } else {
                    if(guns[middle] - a < 0) {
                        start = middle + 1;
                    } else {
                        end = middle;
                    }
                }
            }
        }
        return cnt;
    }
}