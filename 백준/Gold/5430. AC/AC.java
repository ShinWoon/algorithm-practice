import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 함수 R: 배열에 있는 수의 순서를 뒤집는 함수
 * 함수 D: 첫번째 수 버리는 함수 <- 배열이 비어 있을 때 에러 발생
 * 함수를 조합해서 한 번에 사용 가능
 * 함수 수행 결과 출력 <- 에러 발생 시 error 출력
 */
public class Main {
    private static boolean checkStop;
    private static String functionStr;
    private static int N, dir;  // dir 0: front && 1: back
    private static Deque<String> inputDQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            functionStr = br.readLine();
            N = Integer.parseInt(br.readLine());
            inputDQ = new ArrayDeque<>();
            String inputArrStr = br.readLine();
            String[] inputStr = inputArrStr.substring(1, inputArrStr.length() - 1).split(",");
            for (int i = 0; i < inputStr.length; i++) {
                String thisChar = inputStr[i];
                if(thisChar.equals("")) continue;
                inputDQ.add(thisChar);
            }
            dir = 0;
            checkStop = false;
            solve();
            printResult();
        }
    }

    private static void solve() {
        for (int i = 0; i < functionStr.length(); i++) {
            char thisFunc = functionStr.charAt(i);
            if (thisFunc == 'R') {   // 순서 뒤집기
                if(dir == 0) dir = 1;
                else dir = 0;
            } else {    // 앞에 원소 삭제
                if (inputDQ.isEmpty()) {
                    checkStop = true;
                    return;
                }
                if(dir == 0) { // 앞에 제거
                    inputDQ.removeFirst();
                } else {    // 뒤에 제거
                    inputDQ.removeLast();
                }
            }
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        if (checkStop) {
            sb.append("error");
        } else {
            sb.append("[");
            if(dir == 0) {
                while (!inputDQ.isEmpty()) {
                    sb.append(inputDQ.pollFirst());
                    if (!inputDQ.isEmpty()) sb.append(",");
                }
            } else {
                while (!inputDQ.isEmpty()) {
                    sb.append(inputDQ.pollLast());
                    if (!inputDQ.isEmpty()) sb.append(",");
                }
            }
            sb.append("]");
        }
        System.out.println(sb);
    }
}