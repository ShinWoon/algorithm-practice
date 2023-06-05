import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    private static int R, C, maxCnt;    // maxCnt : 내가 갈 수 있는 최대 칸의 수
    private static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1}; // 상하좌우
    private static char[][] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        maxCnt = 0;
        Set<Character> visited = new HashSet<>(); // 내가 같은 알파벳이 적힌 칸을 갈 수 없기 때문에 이를 체크하기 위한 set
        visited.add(map[0][0]);
        dfs(0,0,visited, 1);
        System.out.println(maxCnt);
    }

    private static void dfs(int y, int x, Set<Character> visited, int cnt) {
        maxCnt = Math.max(maxCnt, cnt); // 최대 방문 가능 칸 수 갱신

        for(int i=0; i<4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if(cx<0 || cx>=C || cy<0 || cy>=R) continue;
            if(!visited.contains(map[cy][cx])) {
                // 방문할 수 있는 조건을 만족
                visited.add(map[cy][cx]);
                dfs(cy, cx, visited, cnt + 1);
                visited.remove(map[cy][cx]);
            }
        }
    }
}