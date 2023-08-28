import java.util.*;

class Solution {
    private static class Info {
        int x;
        int y;
        int cnt;
        String route;
        public Info(int x, int y, int cnt, String route) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.route = route;
        }
    }
    private static int[] dy = {0,-1,1,0}, dx = {1,0,0,-1};
    private static String[] dir = {"d", "l", "r", "u"};
    private static Map<String, Integer> dirMap = new HashMap<>();
    private static boolean[][][] visited;
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        dirMap.put("d",0);
        dirMap.put("l",1);
        dirMap.put("r",2);
        dirMap.put("u",3);
        List<String> routeList = new ArrayList<>();
        visited = new boolean[n+1][m+1][k+1];
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(x,y,0, ""));
        visited[x][y][0] = true;

        while(!q.isEmpty()) {
            Info info = q.poll();
            int currentX = info.x;
            int currentY = info.y;
            String currentRoute = info.route;
            int cnt = info.cnt;
//            System.out.println("x " + x + " y " + y);
            if(cnt == k) {
                if((currentX == r && currentY == c)) routeList.add(currentRoute);
                continue;
            }

            for(int i=0; i<4; i++) {
                int cx = currentX + dx[i];
                int cy = currentY + dy[i];
                if(cx <=0 || cx>n || cy<=0 || cy>m) continue;
                if(!visited[cx][cy][cnt + 1]) {
                    q.add(new Info(cx, cy, cnt+1, currentRoute + dir[i]));
                    visited[cx][cy][cnt + 1] = true;
                }
            }
        }
        if(routeList.isEmpty()) {
            return "impossible";
        }
        Collections.sort(routeList);
        String answer = routeList.get(0);
        return answer;
    }
}