import java.util.*;

class Solution {
    private static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                findNetworks(n, i, computers);
                answer += 1;
            }
        }
        return answer;
    }
    private static void findNetworks(int n, int start, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while(!q.isEmpty()) {
            int currentNode = q.poll();
            
            for(int i=0; i<n; i++) {
                if(i == currentNode) continue;
                if(computers[currentNode][i] == 1) {
                    // 연결되어 있으면
                    if(!visited[i]) {
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
        }
    }
}