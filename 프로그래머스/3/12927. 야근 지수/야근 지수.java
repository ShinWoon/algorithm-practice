import java.util.*;

class Solution {
    private static int N;
    private static long answer;
    private static Queue<Integer> workQ;
    public long solution(int n, int[] works) {
        answer = 0;
        N = n;
        // 큰 거부터 나오게 내림차순
        workQ = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // Q에 work 값들 넣고 돌리기
        for(int i=0; i<works.length; i++) {
            workQ.add(works[i]);
        }
        solve();
        getAnswer();
        return answer;
    }
    private static void solve() {
        // 계속 큰 것만 빼서 일 처리
        while(!workQ.isEmpty()) {
            if(N == 0) break;   // 시간 끝나면 그만
            int currentWork = workQ.poll();
            if(currentWork == 0) break; // 남은 일이 없으면
            currentWork -= 1;
            N -= 1; // 시간 뺌
            workQ.add(currentWork);
        }
    }
    private static void getAnswer() {
        // 야근 지수 계산
        while(!workQ.isEmpty()) {
            int num = workQ.poll();
            answer += Math.pow(num, 2);
        }
    }
}