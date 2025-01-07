import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, minNum, maxNum;
    private static int[] numArr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        minNum = Integer.MAX_VALUE;
        maxNum = Integer.MIN_VALUE;

        // 숫자 배열 초기화
        numArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 배열 초기화
        int[] operatorArr = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            operatorArr[i] = Integer.parseInt(st.nextToken());
        }

        solve(numArr[0], 1, operatorArr);
        bw.append(String.valueOf(maxNum)).append("\n");
        bw.append(String.valueOf(minNum));
        bw.flush();
    }

    private static void solve(int num, int depth, int[] operator) {
        if(depth == N) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
            return;
        }

        // 덧셈
        if(operator[0] != 0) {
            operator[0] -= 1;
            solve(num+numArr[depth], depth+1, operator);
            operator[0] += 1;
        }

        // 뺄셈
        if(operator[1] != 0) {
            operator[1] -= 1;
            solve(num-numArr[depth], depth+1, operator);
            operator[1] += 1;
        }

        // 곱셈
        if(operator[2] != 0) {
            operator[2] -= 1;
            solve(num*numArr[depth], depth+1, operator);
            operator[2] += 1;
        }

        // 나눗셈
        if(operator[3] != 0) {
            operator[3] -= 1;
            solve(num/numArr[depth], depth+1, operator);
            operator[3] += 1;
        }
        return;
    }
}