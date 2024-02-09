import java.util.*;

class Solution {
    private static Stack<Integer>[] boardStack;
    private static Stack<Integer> basket;
    private static int result;
    public int solution(int[][] board, int[] moves) {
        result = 0;
        boardStack = new Stack[board.length];
        for(int i=0; i<board.length; i++) {
            boardStack[i] = new Stack<>();
            for(int j=board[0].length - 1; j>=0; j--) {
                boardStack[i].push(board[j][i]);
            }
        }
        
        basket = new Stack<>();
        for(int i=0; i<moves.length; i++) {
            int idx = moves[i] - 1;
            solve(idx);
        }
        return result;
    }
    private static void solve(int idx) {
        int doll = 0;
        while(!boardStack[idx].isEmpty()) {
            int tmp = boardStack[idx].pop();
            if(tmp != 0) {
                doll = tmp;
                break;
            }
        }
        if(doll == 0) return;
        if(basket.isEmpty()) basket.push(doll);
        else {
            int before = basket.peek();
            if(before == doll) {
                basket.pop();
                result += 2;
            } else {
                basket.push(doll);
            }
        }
    }
}