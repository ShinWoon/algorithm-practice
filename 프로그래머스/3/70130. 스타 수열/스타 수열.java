import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[] numArr = new int[a.length];
        for(int num: a) {
            numArr[num] += 1;
        }

        for(int i=0; i<a.length; i++) {
            if(numArr[i] <= answer) continue;
            int cnt = 0;
            for(int j=0; j<a.length-1; j++) {
                if(a[j] == a[j+1]) continue;
                if(a[j] != i && a[j+1] != i) continue;
                // 조건 만족
                cnt += 1;
                j += 1;
            }
            answer = Math.max(answer, cnt);
        }
        return answer * 2;
    }
}