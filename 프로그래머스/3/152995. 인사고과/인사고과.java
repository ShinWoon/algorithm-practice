import java.util.*;

class Solution {
    public int solution(int[][] scores) { 
        int answer = 1;
        int attitude = scores[0][0];
        int peer = scores[0][1];
        
        Arrays.sort(scores, (s1, s2) -> {
           if(s1[0]== s2[0]) {
               return s1[1] - s2[1];
           } else {
               return s2[0] - s1[0];
           }
        });
        
        int maxPeerScore = Integer.MIN_VALUE;
        for(int i=0; i<scores.length; i++) {
            boolean check = true;
            if(maxPeerScore < scores[i][1]) {
                maxPeerScore = scores[i][1];
            } else if (maxPeerScore > scores[i][1]) {
                check = false;  // 인센티브 못 받음
            }
            
            if(!check) {
                if(scores[i][0] == attitude && scores[i][1] == peer) return -1;
            } else {
                if(scores[i][0] + scores[i][1] > attitude + peer) {
                    answer += 1;
                }
            }
        }       
        return answer;
    }
}