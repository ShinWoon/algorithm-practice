import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Problem {
        int num;
        int level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> problemMap = new HashMap<>();
        Queue<Problem> hardQ = new PriorityQueue<>((o1, o2) -> {    // 가장 어려운걸로, 그 중 큰 번호로
            if(o1.level == o2.level) return o2.num - o1.num;
            return o2.level - o1.level;
        });
        Queue<Problem> easyQ = new PriorityQueue<>((o1, o2) -> {    // 가장 쉬운걸로, 그 중 가장 작은 번호로
            if(o1.level == o2.level) return o1.num - o2.num;
            return o1.level - o2.level;
        });
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            hardQ.add(new Problem(number, level));
            easyQ.add(new Problem(number,level));
            problemMap.put(number, level);
        }

        boolean[] solved = new boolean[100_001];
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            // 이미 풀었는거 제거


            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("add")) {
                int number = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                hardQ.add(new Problem(number, level));
                easyQ.add(new Problem(number,level));
                problemMap.put(number, level);
                solved[number] = false; // 혹시 이전에 풀었는데 또 추가
            } else if (command.equals("recommend")) {
                int type = Integer.parseInt(st.nextToken());
                if(type == 1) { // 가장 어려운 거
                    while (true) {
                        Problem thisP = hardQ.poll();
                        if(!solved[thisP.num] && problemMap.get(thisP.num).equals(thisP.level)) {    // 아직 안풀었음
                            sb.append(thisP.num).append("\n");
                            hardQ.add(thisP);   // 추천이니까 다시 넣기
                            break;
                        }
                    }
                } else if(type == -1) {
                    while (true) {
                        Problem thisP = easyQ.poll();
                        if(!solved[thisP.num] && problemMap.get(thisP.num).equals(thisP.level)) {    // 아직 안풀었음
                            sb.append(thisP.num).append("\n");
                            easyQ.add(thisP);
                            break;
                        }
                    }
                }
            } else {    // solved
                int number = Integer.parseInt(st.nextToken());
                solved[number] = true;
                problemMap.remove(number);
            }
        }
        System.out.print(sb);
    }

}