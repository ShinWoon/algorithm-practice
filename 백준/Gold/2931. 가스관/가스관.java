import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static char resultPipe;
    private static int R, C;
    // 상 좌 하 우
    private static int[] findSpot, dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    private static char[][] map;
    private static Map<Character, Integer[]> pipeMap;
    private static Map<String, Character> dirToPipeMap;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String inputStr = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = inputStr.charAt(j);
                map[i][j] = c;
            }
        }

        pipeMap = new HashMap<>();
        dirToPipeMap = new HashMap<>();
        setMap();

        solve();
        sb = new StringBuilder();
        setAnswer();
        System.out.println(sb);
    }

    private static void solve() {
        findSpot = new int[2];

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == '.') {
                    String result = "";
                    for(int d=0; d<4; d++) {
                        int cy = i + dy[d];
                        int cx = j + dx[d];
                        if(cx<0 || cx>=C || cy<0 || cy>=R) {
                            result += "0";
                            continue;
                        }
                        if(map[cy][cx] == '.') result += "0";
                        else {
//                            System.out.println(map[cy][cx] + " " + d + " " + pipeMap.get(map[cy][cx])[(d+2)%4]);
                            if(pipeMap.get(map[cy][cx])[(d+2)%4] == 1) {
                                result += "1";
                            } else {
                                result += "0";
                            }
                        }
                    }
                    if(findPipeType(result)) {
                        findSpot[0] = i + 1;
                        findSpot[1] = j + 1;
                        return;
                    }
                }
            }
        }
    }

    private static boolean findPipeType(String result) {
        if(dirToPipeMap.containsKey(result)) {
//            System.out.println(result);
            resultPipe = dirToPipeMap.get(result);
            return true;
        }
        else return false;
    }

    private static void setMap() {
        // 상 좌 하 우
        pipeMap.put('|', new Integer[]{1, 0, 1, 0});
        pipeMap.put('-', new Integer[]{0, 1, 0, 1});
        pipeMap.put('+', new Integer[]{1, 1, 1, 1});
        pipeMap.put('1', new Integer[]{0, 0, 1, 1});
        pipeMap.put('2', new Integer[]{1, 0, 0, 1});
        pipeMap.put('3', new Integer[]{1, 1, 0, 0});
        pipeMap.put('4', new Integer[]{0, 1, 1, 0});
        pipeMap.put('M', new Integer[]{0, 0, 0, 0});
        pipeMap.put('Z', new Integer[]{0, 0, 0, 0});

        dirToPipeMap.put("1010", '|');
        dirToPipeMap.put("0101", '-');
        dirToPipeMap.put("1111", '+');
        dirToPipeMap.put("0011", '1');
        dirToPipeMap.put("1001", '2');
        dirToPipeMap.put("1100", '3');
        dirToPipeMap.put("0110", '4');
    }

    private static void setAnswer() {
        sb.append(findSpot[0]);
        sb.append(" ");
        sb.append(findSpot[1]);
        sb.append(" ");
        sb.append(resultPipe);
    }
}