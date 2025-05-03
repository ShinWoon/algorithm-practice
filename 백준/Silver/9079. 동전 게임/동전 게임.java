import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Info {
        String curStr;
        int cnt;
        Info(String curStr, int cnt) {
            this.curStr = curStr;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for(int T=0; T<testCase; T++) {
            String inputStr = "";
            for(int i=0; i<3; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++) {
                    inputStr += st.nextToken();
                }
            }
            int result = solve(inputStr);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    private static int solve(String inputStr) {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(inputStr, 0));
        Set<String> strSet = new HashSet<>();
        strSet.add(inputStr);

        while (!q.isEmpty()) {
            Info curInfo = q.poll();
            if(curInfo.curStr.equals("HHHHHHHHH") || curInfo.curStr.equals("TTTTTTTTT")) {
                return curInfo.cnt;
            }

            for(int i=0; i<8; i++) {
                String nextStr = change(i, curInfo.curStr);
                if(!strSet.contains(nextStr)) {
                    q.add(new Info(nextStr, curInfo.cnt + 1));
                    strSet.add(nextStr);
                }
            }
        }
        return -1;
    }
    private static String change(int type, String inputStr) {
        String nextStr = "";
        if(type < 3) {
            // 세로
            for(int i=0; i<9; i++) {
                if(i%3 == type) {
                    nextStr += swap(inputStr.charAt(i));
                } else {
                    nextStr += inputStr.charAt(i);
                }
            }
        } else if (type < 6) {
            // 가로
            for(int i=0; i<9; i++) {
                if(i/3 == type%3) {
                    nextStr += swap(inputStr.charAt(i));
                } else {
                    nextStr += inputStr.charAt(i);
                }
            }
        } else if(type == 6) {
            // 대각선
            for(int i=0; i<9; i++) {
                if(i == 0 || i == 4 || i == 8) {
                    nextStr += swap(inputStr.charAt(i));
                } else {
                    nextStr += inputStr.charAt(i);
                }
            }
        } else {
            for(int i=0; i<9; i++) {
                if(i == 2 || i == 4 || i == 6) {
                    nextStr += swap(inputStr.charAt(i));
                } else {
                    nextStr += inputStr.charAt(i);
                }
            }
        }
        return nextStr;
    }

    private static String swap(char c) {
        if(c == 'H') return "T";
        else return "H";
    }
}
