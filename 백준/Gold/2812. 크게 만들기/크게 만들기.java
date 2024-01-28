import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        number = new int[N];
        String inputStr = br.readLine();
        for(int i=0; i<N; i++) {
            number[i] = Integer.parseInt(String.valueOf(inputStr.charAt(i)));
        }

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i=0; i<N; i++) {    // 배열 돌면서 스택에 넣고 값 확인해서 높은 값 넣기
            if(stack.isEmpty()) {
                stack.push(number[i]);
            } else {
                while(true) {
                    if(count == K || stack.isEmpty()) {
                        stack.push(number[i]);
                        break;
                    } else {
                        int before = stack.peek();
                        if(before >= number[i]) {
                            stack.push(number[i]);
                            break;
                        } else {
                            stack.pop();
                            count++;
                        }
                    }
                }
            }
        }

        // 만약에 뺀적이 없으면 그냥 뒤에서 쭉 빼자
        if(count != K) {
            for(int i=0; i<K-count; i++) stack.pop();
        }

        // 결과 출력
        Iterator iter = stack.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next());
        }
    }
}