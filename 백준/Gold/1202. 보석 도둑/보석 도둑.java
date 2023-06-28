import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Jewelry {
        int weight;
        int price;

        public Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Jewelry{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }

    private static int N, K;
    private static int[] bags;
    private static List<Jewelry> jewelrys;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        jewelrys = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            int w = Integer.parseInt(tmp[0]);
            int p = Integer.parseInt(tmp[1]);
            jewelrys.add(new Jewelry(w, p));
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        jewelrys.sort(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                if (o1.weight == o2.weight) {
                    return o2.price - o1.price;
                }
                return o1.weight - o2.weight;
            }
        });

        Arrays.sort(bags);
//        for(int i=0; i<N; i++) System.out.println(jewelrys.get(i).toString());;
        long result = 0;
        int idx = 0;
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
//        boolean[] visited = new boolean[N];
        for (int i = 0; i < K; i++) {
            int c = bags[i];    // 가방 최대 무게
//            q = new PriorityQueue<>(Collections.reverseOrder());
            while (true) {
                if(idx >= N) break;
//                System.out.println("c " + c + " weight " + jewelrys.get(idx).weight);
                if (jewelrys.get(idx).weight <= c) {
//                    result += jewelrys.get(idx).price;
                    q.add(jewelrys.get(idx).price);
//                    visited[idx] = true;
                } else if (jewelrys.get(idx).weight > c) {
                    break;
                }
                idx++;
            }
            if(!q.isEmpty()) {
                result += q.poll();
//                System.out.println(result);
            }
        }

        System.out.println(result);
    }
}