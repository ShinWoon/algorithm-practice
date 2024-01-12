import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, endTime;

    private static class Func {
        String function;
        String input;
        int index;

        public Func(String function, String input, int index) {
            this.function = function;
            this.input = input;
            this.index = index;
        }
    }

    private static List<Func> funcList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        funcList = new ArrayList<>();
        endTime = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String func = st.nextToken();
            String input = st.nextToken();
            int index = Integer.parseInt(st.nextToken());
            funcList.add(new Func(func, input, index));
            endTime = Math.max(endTime, index);
        }

        solve();
    }

    private static void solve() {
        Stack<String> wordStack = new Stack<>();
        int index = endTime;
        for (int i = N - 1; i >= 0; i--) {
            if (funcList.get(i).index < index) index = funcList.get(i).index;   // 다음 넘어간게 더 적으면 그냥 그걸로 값 바꿔주기
            else if (funcList.get(i).index > index) continue;
            if (funcList.get(i).function.equals("type")) {
                wordStack.push(funcList.get(i).input);
                index--;
            } else {
                int time = Integer.parseInt(funcList.get(i).input);
                index -= time + 1;
            }
        }
        String result = "";
        while (!wordStack.isEmpty()) result += wordStack.pop();
        System.out.println(result);
    }
}