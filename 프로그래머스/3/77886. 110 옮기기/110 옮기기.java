import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        StringBuilder sb;
        String[] answer = new String[s.length];
        
        for(int i=0; i<s.length; i++) {
            String thisStr = s[i];
            Stack<String> stack = new Stack<>();
            int cntOneOneZero = 0;
            sb = new StringBuilder();
            for(int j=0; j<thisStr.length(); j++) {
                stack.push(String.valueOf(thisStr.charAt(j)));
                
                if(stack.size() >= 3) {
                    String tmpStr = "";
                    for(int t=0; t<3; t++) tmpStr += stack.pop();
                    if(tmpStr.equals("011")) {
                        cntOneOneZero += 1;
                    } else {
                        for(int t=2; t>=0; t--) {
                            stack.push(String.valueOf(tmpStr.charAt(t)));
                        }
                    }
                }
            }
            
            // 110 빠진 문자들이 스택에 쌓여 있음
            // 돌면서 0 뒤에 110 붙여주기
            while(!stack.isEmpty()) {
                String thisNum = stack.pop();
                if(thisNum.equals("0")) {
                    if(cntOneOneZero > 0) {
                        while(cntOneOneZero > 0) {
                            sb.append("011");
                            cntOneOneZero -= 1;
                        }
                    }
                }
                sb.append(thisNum);
            }
            if(cntOneOneZero != 0) {
                while(cntOneOneZero > 0) {
                    sb.append("011");
                    cntOneOneZero -= 1;
                }
            }
            answer[i] = sb.reverse().toString();
        }
        return answer;
    }
}