import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int A, B, N, M, X, Y;
    private static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    private static int[][] robotInfo, map;
    private static Map<String, Integer> dirMap;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        A = Integer.parseInt(tmp[0]);
        B = Integer.parseInt(tmp[1]);

        tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        dirMap = new HashMap<>();
        setMap();

        map = new int[B+1][A+1];
        robotInfo = new int[N+1][];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            String dir = tmp[2];
            map[y][x] = i+1;
            robotInfo[i+1] = new int[]{x, y, dirMap.get(dir)};
        }

        int result = 0;
        for(int i=0; i<M; i++) {
            tmp = br.readLine().split(" ");
            int robot = Integer.parseInt(tmp[0]);
            String command = tmp[1];
            int repeat = Integer.parseInt(tmp[2]);
            result = solve(robot, command, repeat);
            if(result != 0) {
                // 충돌한 경우
                break;
            }
//            for(int y=1; y<=B; y++) {
//                for(int x=1; x<=A; x++) {
//                    System.out.print(map[y][x] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        if(result == 0) System.out.println("OK");
        else if(result == 1) System.out.println("Robot " + X + " crashes into the wall");
        else System.out.println("Robot " + X + " crashes into robot " + Y);
    }

    private static int solve(int robot, String command, int repeat) {
        int x = robotInfo[robot][0];
        int y = robotInfo[robot][1];
        int dir = robotInfo[robot][2];
        if(command.equals("F")) {
            for(int r=0; r<repeat; r++) {
                int cx = x + dx[dir];
                int cy = y + dy[dir];
                if(cx<=0 || cx>A || cy<=0 || cy>B) {
                    // 벽에 충돌한 경우
                    X = robot;
                    return 1;
                } else if (map[cy][cx] != 0) {
                    // 다른 로봇에 충돌한 경우
                    X = robot;
                    Y = map[cy][cx];
                    return 2;
                }
                map[y][x] = 0;
                map[cy][cx] = robot;
                x = cx;
                y = cy;
            }
            robotInfo[robot][0] = x;
            robotInfo[robot][1] = y;
        } else if (command.equals("L")) {
            for(int r=0; r<repeat; r++) {
                dir -= 1;
                if(dir == -1) dir = 3;
            }
        } else {
            for(int r=0; r<repeat; r++) {
                dir += 1;
            }
            dir %= 4;
        }

        robotInfo[robot][2] = dir;
        return 0;
    }

    private static void setMap() {
        dirMap.put("N", 0);
        dirMap.put("E", 1);
        dirMap.put("S", 2);
        dirMap.put("W", 3);
    }
}