import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int bIdx = A.length-1;
        for(int i=A.length-1; i>=0; i--) {
            // B가 A보다 커야지만 승점
            if(B[bIdx] > A[i]) {
                answer += 1;
                bIdx--;
            }
        }
        return answer;
    }
    // private static void permutation(int n, int[] arr, int[] A, int[] B) {
    //     if(n == A.length) {
    //         int cnt = 0;
    //         for(int i=0; i<A.length; i++) {
    //             if(A[i] < B[arr[i]]) cnt++;
    //         }
    //         answer = Math.max(answer, cnt);
    //     }
    //     for(int i=0; i<A.length; i++) {
    //         if(!visited[i]) {
    //             visited[i] = true;
    //             arr[n] = i;
    //             permutation(n+1, arr, A, B);
    //             visited[i] = false;
    //         }
    //     }
    // }
}