import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static Queue<Integer> minQ, maxQ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        minQ = new PriorityQueue<>();
        maxQ = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(minQ.size() == maxQ.size()) {
                // 크기 같으면 maxQ에 넣기
                maxQ.add(num);
            } else {
                minQ.add(num);
            }
            if(!minQ.isEmpty() && !maxQ.isEmpty()) {
                // maxQ front 값과 minQ front 값 비교
                // maxQ front 값이 minQ front 값 보다 적어야 함
                if(maxQ.peek() > minQ.peek()) {
                    // 둘이 값 바꿔줘야함
                    int minNum = minQ.poll();
                    int maxNum = maxQ.poll();
                    minQ.add(maxNum);
                    maxQ.add(minNum);
                }
            }
            // 매번 middle 값 == maxQ의 front 값
            sb.append(maxQ.peek());
            sb.append("\n");
        }

        System.out.println(sb);
    }
}