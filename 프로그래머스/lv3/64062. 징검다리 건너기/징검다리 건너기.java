class Solution {
    static int answer;
    public int solution(int[] stones, int k) {
        answer = 0;
        int start = 1;
        int end = 400_000_000;
        int middle = 0;
        while(start <= end){
            middle = (start+end)/2;
            // System.out.println(middle);
            if(solve(stones, k, middle)) {
                start = middle + 1;
                answer = middle;
            } else {
                end = middle - 1;
            }
        }
        return answer;
    }
    
    private static boolean solve(int[] stones, int k, int thisFriendCnt) {
        int idx = 0;
        for(int i=0; i<stones.length; i++) {
            int thisStone = stones[i];
            // System.out.println(thisStone + " idx " + idx);
            if(thisStone < thisFriendCnt) {
                idx++;
            } else {
                idx = 0;
            }
            if(idx >= k) return false;   // 못 가니까
        }
        return true;
    }
}