import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static class Homework {
        int dueDate;
        int score;
        public Homework(int dueDate, int score) {
            this.dueDate = dueDate;
            this.score = score;
        }
    }
    private static int N, maxDate;
    private static int[] scoreArr;
    private static Queue<Homework> q;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // score로 내림차순 정렬
        q = new PriorityQueue<>(new Comparator<Homework>() {
            @Override
            public int compare(Homework o1, Homework o2) {
                if(o1.score > o2.score) {
                    return -1;
                }
                return 1;
            }
        });
        maxDate = 0;

        for(int i=0; i<N; i++) {
            String[] tmp = br.readLine().split(" ");
            int dDate = Integer.parseInt(tmp[0]);
            int score = Integer.parseInt(tmp[1]);
            maxDate = Math.max(maxDate, dDate);
            q.add(new Homework(dDate,score));
        }

        solve();
        int result = 0;
        // 저장되어 있는 점수 값들 더하기
        for(int i=1; i<=maxDate; i++) {
            result += scoreArr[i];
        }
        System.out.println(result);
    }

    private static void solve() {
        scoreArr = new int[maxDate+1];

        // 큐에 있는 값을 빼면서 내 due date 이하인 곳에서 처리하면 되니까 due date 이하로 빈 곳에 넣자
        while(!q.isEmpty()) {
            Homework hwInfo = q.poll();
            for(int i=hwInfo.dueDate; i>=1; i--) {
                if(scoreArr[i] == 0) {
                    scoreArr[i] = hwInfo.score;
                    break;
                }
            }
        }
    }
}