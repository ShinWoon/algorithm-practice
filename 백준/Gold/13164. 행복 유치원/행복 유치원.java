import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class Info {
        int diff;
        int idx;

        public Info(int diff, int idx) {
            this.diff = diff;
            this.idx = idx;
        }
    }
    static int N, K, minCost;
    static int[] studentHeights;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        studentHeights = new int[N];
        tmp = br.readLine().split(" ");
        for(int i=0; i<N; i++) studentHeights[i] = Integer.parseInt(tmp[i]);

        solve();
        System.out.println(minCost);
    }

    private static void solve() {
        minCost = (int)1e9;
        Queue<Info> diffQ = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.diff - o1.diff;
            }
        });
        Queue<Info> idxQ = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.idx - o2.idx;
            }
        });
        for(int i=0; i<N-1; i++) {
            diffQ.add(new Info(studentHeights[i+1] - studentHeights[i], i));
        }

        // 키 순으로 정렬 -> K-1개 뽑기
        for(int i=0; i<(K-1); i++) idxQ.add(diffQ.poll());
        // 뽑은 애들 idx 순으로 정렬 -> 돌면서 비용 계산하기
        cntCost(idxQ);
    }


    // 비용 계산하기
    private static void cntCost(Queue<Info> q) {
        int cost = 0;
        int start = 0;
        int until = 0;
        for(int i=0; i<(K-1); i++) {
            until = q.poll().idx;
//            System.out.println("until " + until);
            cost += (studentHeights[until] - studentHeights[start]);
//            System.out.println(cost);
            start = until+1;    // 다음 시작은 이번 경계에서 +1인 곳에서 시작
        }
        // 마지막 그룹
        cost += (studentHeights[N-1] - studentHeights[start]);
        minCost = cost;
    }
}