class Solution {
    private static int result;
    private static int[][] dp;
    public int solution(int sticker[]) {
        int answer = 0;
        dp = new int[2][sticker.length];
        result = 0;
        solve(0, sticker);
        solve(1, sticker);
        answer = result;
        return answer;
    }
    private static void solve(int start, int[] sticker){
        for(int i=start; i<start+3; i++) {
            if(i >= sticker.length) continue;
            dp[start][i] = sticker[i];
            if(i-2 >= start) dp[start][i] += sticker[i-2]; 
            result = Math.max(result, dp[start][i]);
        }
        for(int i=start+3; i<sticker.length-1+start; i++) {
            dp[start][i] = Math.max(dp[start][i-2], dp[start][i-3]) + sticker[i];
            result = Math.max(result, dp[start][i]);
            // System.out.println(dp[start][i]);
        }
    }
}