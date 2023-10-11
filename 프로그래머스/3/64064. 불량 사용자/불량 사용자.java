import java.util.*;

class Solution {
    private static int N;
    private static boolean[][] visited;
    private static Set<Set<String>> resultSet;
    private static List<List<String>> bannedIdList;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        resultSet = new HashSet<>();
        N = banned_id.length;
        
        // 제재 아이디 찾기
        bannedIdList = new ArrayList<>();
        for(String id : banned_id) {
            List<String> tmp = new ArrayList<>();
            for(String uid: user_id) {
                if(id.length() != uid.length()) continue;
                boolean check = true;
                for(int i=0; i<id.length(); i++) {
                    if(id.charAt(i) == '*') continue;
                    if(id.charAt(i) != uid.charAt(i)) {
                        check = false;
                        break;
                    }
                }
                if(check) tmp.add(uid);
            }
            bannedIdList.add(tmp);
        }
        // for(int i=0; i<bannedIdList.size(); i++) {
        //     for(int j=0; j<bannedIdList.get(i).size(); j++) {
        //         System.out.print(bannedIdList.get(i).get(j) + " ");
        //     }
        //     System.out.println();
        // }
        
        visited = new boolean[N][];
        for(int i=0; i<N; i++) visited[i] = new boolean[bannedIdList.get(i).size()];
        // 제재 아이디 조합
        combination(0,0,new String[N]);
        // 조합 중복되는지 체크
        
        
        answer = resultSet.size();
        return answer;
    }
    
    private static void combination(int start, int idx, String[] ids) {
        if(start == N) {
            checkIds(ids);
            return;
        }
        
        for(int i=idx; i<bannedIdList.get(start).size(); i++) {
            if(!visited[start][i]) {
                visited[start][i] = true;
                ids[start] = bannedIdList.get(start).get(i);
                combination(start+1, idx, ids);
                visited[start][i] = false;
            }
        }
        
        
    }
    private static void checkIds(String[] ids) {
        // for(int i=0; i<N; i++) System.out.print(ids[i] + " ");
        // System.out.println();
        Set<String> tmpSet = new HashSet<>(Arrays.asList(ids));
        if(tmpSet.size() == N) {
            if(!resultSet.contains(tmpSet)) resultSet.add(tmpSet);
        }
    }
}