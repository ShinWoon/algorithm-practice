import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, X;
    private static int[] visitedAmount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        X = Integer.parseInt(tmp[1]);

        visitedAmount = new int[N];
        tmp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) visitedAmount[i] = Integer.parseInt(tmp[i]);

        // X일 동안 가장 많이 들어온 방문자 수 확인, 같은 최댓값인 경우 구간 몇개 인지 확인
        int visitedSum = 0;
        for(int i=0; i<X; i++) visitedSum += visitedAmount[i];

        int maxVisited = visitedSum;
        int termCnt = 1;

        for (int i = X; i < N; i++) {
            visitedSum += visitedAmount[i] - visitedAmount[i - X];
            if (maxVisited < visitedSum) {
                maxVisited = visitedSum;
                termCnt = 1;
            } else if (maxVisited == visitedSum) {
                termCnt++;
            }

        }

        if (maxVisited == 0) sb.append("SAD");
        else sb.append(maxVisited).append("\n").append(termCnt);
        System.out.println(sb);
    }
}