import java.util.*;

class Solution {
    private static Set<Integer> numSet;
    private static int[] leftArr, rightArr;
    public int solution(int[] a) {
        int answer = 0;
        numSet = new HashSet<>();
        leftArr = new int[a.length];
        rightArr = new int[a.length];
        int leftMin = a[0];
        int rightMin = a[a.length-1];
        
        for(int i=0; i<a.length; i++) {
            leftMin = Math.min(leftMin, a[i]);
            leftArr[i] = leftMin;
        }
        for(int i=a.length-1; i>=0; i--) {
            rightMin = Math.min(rightMin, a[i]);
            rightArr[i] = rightMin;
        }
        for(int i=0; i<a.length; i++) {
            if(!(leftArr[i] < a[i] && rightArr[i] < a[i])) answer++;
        }
        return answer;
    }
    
//     private static void getLastNum(int start, int end, int cnt) {
//         if(start == end) {
//             // 1개 남았을 때
//             numSet.add(arr[start]);
//             return;
//         }
        
//         if(cnt == 0) {
//             if(arr[start] > arr[end]) {
//                 if(end != arr.length-1) getLastNum(start, end+1, cnt+1);
//                 else if(start != 0) getLastNum(start-1, start, cnt+1);
//                 else getLastNum(start, start, cnt+1);
//             }
//             else {
//                 if(start != 0) getLastNum(start-1, end, cnt+1);
//                 else if(end != arr.length-1) getLastNum(end, end+1, cnt+1);
//                 else getLastNum(end, end, cnt+1);
//             }
//         }
//         if(arr[start] > arr[end]) {
//                 if(start != 0) getLastNum(start-1, end, cnt);
//                 else if(end != arr.length-1) getLastNum(end, end+1, cnt);
//                 else getLastNum(end, end, cnt);
//             }
//             else {
//                 if(end != arr.length-1) getLastNum(start, end+1, cnt);
//                 else if(start != 0) getLastNum(start-1, start, cnt);
//                 else getLastNum(start, start, cnt);
//             }
//     }
}