import java.util.*;

class Solution {
    private static int N;
    public long solution(int[] sequence) {
        N = sequence.length;
        
        int[] minusArr = firstMinus(sequence);
        int[] plusArr = firstPlus(sequence);
        
        long[] dpOne = new long[N];
        long[] dpTwo = new long[N];
        dpOne[0] = minusArr[0];
        dpTwo[0] = plusArr[0];
        long answer = Math.max(dpOne[0], dpTwo[0]);
        for(int i=1; i<N; i++) {
            dpOne[i] = Math.max(minusArr[i], dpOne[i-1] + minusArr[i]);
            dpTwo[i] = Math.max(plusArr[i], dpTwo[i-1] + plusArr[i]);
            answer = Math.max(answer, Math.max(dpOne[i], dpTwo[i]));
        }
        return answer;
    }
    private static int[] firstMinus(int[] arr) {
        int[] tmpArr = new int[N];
        for(int i=0; i<N; i++) {
            if(i%2 == 0) {
                tmpArr[i] = -1 * arr[i];
            } else {
                tmpArr[i] = arr[i];
            }
        }
        return tmpArr;
    }
    private static int[] firstPlus(int[] arr) {
        int[] tmpArr = new int[N];
        for(int i=0; i<N; i++) {
            if(i%2 == 0) {
                tmpArr[i] = arr[i];
            } else {
                tmpArr[i] = -1 * arr[i];
            }
        }
        return tmpArr;
    }
}