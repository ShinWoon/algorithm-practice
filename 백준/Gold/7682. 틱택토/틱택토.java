import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int cntX, cntO;
    private static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String inputStr = br.readLine();
            if(inputStr.equals("end")) break;

            map = new char[3][3];
            cntX = 0;
            cntO = 0;
            for(int i=0; i<9; i++) {
                map[i/3][i%3] = inputStr.charAt(i);
                if(map[i/3][i%3] == 'X') cntX++;
                if(map[i/3][i%3] == 'O') cntO++;
            }

            String answer = "valid";
            if(cntX >= cntO) {
                if(cntX > cntO + 1) answer = "invalid";
                else answer = checkTikTakToe();
            } else {    // X가 먼저라서
                answer = "invalid";
            }
            System.out.println(answer);
        }
    }
    private static String checkTikTakToe() {
        // 가로 확인
        int cntXRow = 0;
        int cntORow = 0;
        for(int i=0; i<3; i++) {
            String line = "";
            for(int j=0; j<3; j++) {
                line += map[i][j];
            }
            if(line.equals("XXX")) {
                cntXRow++;
            } else if (line.equals("OOO")) {
                cntORow++;
            }
        }
        if(cntORow == 1 && cntXRow == 1) {
            return "invalid";
        } else if (cntORow > 1 || cntXRow > 1) {
            return "invalid";
        } else if (cntORow == 1 && cntX != cntO) {
            return "invalid";
        } else if(cntXRow == 1 && cntX == cntO) {
            return "invalid";
        }

        // 세로 확인
        int cntXCol = 0;
        int cntOCol = 0;
        for(int i=0; i<3; i++) {
            String line = "";
            for(int j=0; j<3; j++) {
                line += map[j][i];
            }
            if(line.equals("XXX")) {
                cntXCol++;
            } else if (line.equals("OOO")) {
                cntOCol++;
            }
        }
        if(cntOCol == 1 && cntXCol == 1) {
            return "invalid";
        } else if (cntOCol > 1 || cntXCol > 1) {
            return "invalid";
        } else if (cntOCol == 1 && cntX != cntO) {
            return "invalid";
        } else if (cntXCol == 1 && cntX == cntO) {
            return "invalid";
        }

        // 대각선
        int cntXCross = 0;
        int cntOCross = 0;
        String line = String.valueOf(map[0][0]);
        line += map[1][1];
        line += map[2][2];
        if(line.equals("XXX")) {
            cntXCross++;
        } else if (line.equals("OOO")){
            cntOCross++;
        }

        line = String.valueOf(map[0][2]);
        line += map[1][1];
        line += map[2][0];
        if(line.equals("XXX")) {
            cntXCross++;
        } else if (line.equals("OOO")){
            cntOCross++;
        }

        if (cntOCross == 1 && cntX != cntO) {
            return "invalid";
        } else if (cntXCross == 1 && cntX == cntO) {
            return "invalid";
        }

        if(cntXRow + cntXCol + cntXCross == 0 && cntORow + cntOCol + cntOCross == 0) {
            if(cntX != 5 && cntO != 4) return "invalid";
        }
        return "valid";
    }
}