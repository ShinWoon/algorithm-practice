import java.io.*;
import java.util.*;

public class Main {
    static boolean check;
    static int R, C, X, Y, turn;
    static List<String>[][] map;
    static List<Integer> directionList;
    static Set<Integer[]> robotSpots;
    static int[] dx = {0,-1,0,1,-1,0,1,-1,0,1}, dy = {0,1,1,1,0,0,0,-1,-1,-1};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        String input;
        map = new ArrayList[R][C];
        robotSpots = new HashSet<>();
        for(int i=0; i<R; i++){
            input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = new ArrayList<>();
                String s = ""+input.charAt(j);
                if(s.equals("I")) {
                    X = j;
                    Y = i;
                } else if(s.equals("R")) {
                    robotSpots.add(new Integer[]{i,j});
                    map[i][j].add(s);
                }
            }
        }
        input = br.readLine();
        directionList = new ArrayList<>();
        for(int i=0; i<input.length(); i++){
            directionList.add(Integer.parseInt("" + input.charAt(i)));
        }

        if(solve()) {
            // 게임이 다 수행된 경우
            printMap();
        } else {
            // 게임이 중간에 끝난 경우
            bw.append("kraj ");
            bw.append(""+turn);
        }
        bw.flush();
    }

    // 방향
    public static boolean solve() {
        turn = 0;
        check = true;
        for(int i=0; i<directionList.size(); i++) {
            turn += 1;
            X += dx[directionList.get(i)];
            Y += dy[directionList.get(i)];
//            System.out.println("x " + X + " y " + Y);
            if(map[Y][X].size() >= 1 && map[Y][X].get(0).equals("R")){
                // 종수가 미친 아두이노가 있는 곳으로 이동한 경우
                return false;
            }

            reSetMap();
            Set<Integer[]> tmpList = new HashSet<>();
            Iterator iterator = robotSpots.iterator();
            while(iterator.hasNext()) {
                Integer[] moveXY = findWay((Integer[]) iterator.next());
                map[moveXY[0]][moveXY[1]].add("R");
                tmpList.add(moveXY);
            }
//            printMap();
//            System.out.println(tmpList.size());
            robotSpots = setMap(tmpList);
//            printMap();
//            System.out.println(robotSpots.size());
            if(!check) return false;
        }
        return true;
    }

    public static Integer[] findWay(Integer[] xy) {
        Integer[] resultXY = new Integer[2];
        int y = xy[0];
        int x = xy[1];
        int minLen = (int)1e9;

        int cx, cy;
        for(int i=1; i<=9; i++) {
            cx = x + dx[i];
            cy = y + dy[i];
            int len = Math.abs(Y-cy) + Math.abs(X-cx);
            if(len < minLen) {
                resultXY[0] = cy;
                resultXY[1] = cx;
                minLen = len;
            }
        }
//        System.out.println("x " + x + " y " + y + " cx " + resultXY[1] + " cy " + resultXY[0]);
        return resultXY;
    }
    public static Set<Integer[]> setMap(Set<Integer[]> tmpList) {
        Set<Integer[]> remainSpot = new HashSet<>();
        Iterator iterator = tmpList.iterator();
        while(iterator.hasNext()) {
            Integer[] info = (Integer[]) iterator.next();
            if(info[0]==Y && info[1]==X) {
                check = false;
                return remainSpot;
            } else if(map[info[0]][info[1]].size() == 1) {
                remainSpot.add(new Integer[]{info[0], info[1]});
            } else if (map[info[0]][info[1]].size() > 1) map[info[0]][info[1]] = new ArrayList<>();
        }
        return remainSpot;
    }

    public static void reSetMap() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) map[i][j] = new ArrayList<>();
        }
    }
    public static void printMap() throws IOException {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(i == Y && j == X) bw.append("I");
                else if(map[i][j].size() == 0) bw.append('.');
                else bw.append(map[i][j].get(0));
            }
            bw.append("\n");
        }
    }
}