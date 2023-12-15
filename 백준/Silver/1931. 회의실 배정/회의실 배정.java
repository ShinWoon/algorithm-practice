import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        String[] tmp;
        for (int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            schedule[i][0] = start;
            schedule[i][1] = end;
        }

        Arrays.sort(schedule, new Comparator<int[]>() { // 먼저 끝나는 애들로 정렬. 같으면 시작이 빠른걸로
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int result = 0;
        int lastTime = 0;
        for(int i=0; i<N; i++) {
            if(schedule[i][0] >= lastTime) {
                result++;
                lastTime = schedule[i][1];
            }
        }
        System.out.println(result);
    }
}