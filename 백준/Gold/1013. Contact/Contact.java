import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean checkFront, checkBack;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int testcase=0; testcase<T; testcase++) {

            String inputStr = br.readLine();
            if(solve(inputStr)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean solve(String inputStr) {
        boolean check = true;
        int idx = 0;
        while(true) {
            checkFront = false;
            checkBack = false;
            int idxFront = solveFront(idx, inputStr);
            int idxBack = solveBack(idx, inputStr);

//            System.out.println("checkFront : " + checkFront + " checkBack : " + checkBack);
            if(!checkFront && !checkBack) {
                check = false;
                break;
            } else if(checkFront){
                idx = idxFront;
            } else if (checkBack) {
                idx = idxBack;
            }

            if(idx >= inputStr.length()) break;
        }
        return check;
    }

    // 01을 만족하는지 확인
    private static int solveFront(int idx, String inputStr) {
        if(idx == inputStr.length()-1) return idx;
        if(inputStr.charAt(idx) == '0' && inputStr.charAt(idx+1) == '1') {
            checkFront = true;
            return idx+2;
        }
        return idx;
    }

    // 100001111를 만족하는지 확인
    private static int solveBack(int idx, String inputStr) {
        if((idx < inputStr.length()-3) && inputStr.substring(idx, idx+3).equals("100")) {
            idx += 2;
            while(idx < inputStr.length() && inputStr.charAt(idx) == '0') {
                idx++;
            }
            if(idx >= inputStr.length()) return idx;
            if(inputStr.charAt(idx) == '1') {
                while(idx < inputStr.length() && inputStr.charAt(idx) == '1') {
                    idx++;
                }
                checkBack = true;
            }
            return idx;
        } else if(((idx > 1) && (idx<inputStr.length()-2)) && inputStr.charAt(idx-2) == '1' && inputStr.substring(idx-1, idx+2).equals("100")){
//            System.out.println(inputStr.substring(idx-1, idx+2));
            idx += 1;
            while(idx < inputStr.length() && inputStr.charAt(idx) == '0') {
                idx++;
            }
            if(idx >= inputStr.length()) return idx;
            if(inputStr.charAt(idx) == '1') {
                while(idx < inputStr.length() && inputStr.charAt(idx) == '1') {
                    idx++;
                }
                checkBack = true;
            }
            return idx;
        }else {
            return idx;
        }
    }
}

// 가장 앞에 있는 두개가 01로 시작하는지 확인
// 01로 시작안하면 100+1+을 만족하는지 확인