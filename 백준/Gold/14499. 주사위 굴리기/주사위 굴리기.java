import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, M ,K;
    static int[] directions, dx = {0,1,-1,0,0} , dy = {0,0,0,-1,1};
    static int[][] map, dice = {{0}, {0,0,0}, {0}, {0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        int sy = Integer.parseInt(tmp[2]);
        int sx = Integer.parseInt(tmp[3]);
        K = Integer.parseInt(tmp[4]);

        map = new int[N][M];
        for(int i=0; i<N; i++){
            tmp = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        directions = new int[K];
        tmp = br.readLine().split(" ");
        for(int i=0; i<K; i++) directions[i] = Integer.parseInt(tmp[i]);

        solve(sx, sy);
    }

    public static void solve(int x, int y) {
        int cx, cy;
        for(int i=0; i<K; i++) {
            cx = x + dx[directions[i]];
            cy = y + dy[directions[i]];
            if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
            // 바깥으로 안나가는 경우
            x = cx;
            y = cy;
            moveDice(directions[i]);
            if(map[y][x] == 0) {
                // 이동한 곳 지도가 0인 경우 주사위 바닥에 있는 값이 지도에 찍힘
                map[y][x] = dice[3][0];
            } else {
                // 지도에 있는 값이 주사위 바닥에 복사되고 지도 칸 값은 0이 됨
                dice[3][0] = map[y][x];
                map[y][x] = 0;
            }
            System.out.println(dice[1][1]);
        }
    }

    public static void moveDice(int d) {
        int[][] tmpDice = new int[4][];
        for(int i=0; i<4; i++) tmpDice[i] = dice[i].clone();

        if(d == 1) {
            // 동쪽으로 이동한 경우
            tmpDice[1][0] = dice[3][0];
            tmpDice[1][1] = dice[1][0];
            tmpDice[1][2] = dice[1][1];
            tmpDice[3][0] = dice[1][2];
        } else if (d == 2) {
            // 서쪽으로 이동한 경우
            tmpDice[1][0] = dice[1][1];
            tmpDice[1][1] = dice[1][2];
            tmpDice[1][2] = dice[3][0];
            tmpDice[3][0] = dice[1][0];
        } else if (d == 3) {
            // 북쪽으로 이동한 경우
            tmpDice[0][0] = dice[1][1];
            tmpDice[1][1] = dice[2][0];
            tmpDice[2][0] = dice[3][0];
            tmpDice[3][0] = dice[0][0];
        } else {
            // 남쪽으로 이동한 경우
            tmpDice[0][0] = dice[3][0];
            tmpDice[1][1] = dice[0][0];
            tmpDice[2][0] = dice[1][1];
            tmpDice[3][0] = dice[2][0];
        }

        for(int i=0; i<4; i++) dice[i] = tmpDice[i].clone();
    }
}