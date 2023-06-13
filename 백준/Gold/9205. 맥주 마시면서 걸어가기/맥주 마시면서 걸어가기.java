import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] house, festival;
    private static List<Integer[]> spots;   // 집 빼고 좌표 저장 리스트
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        house = new int[2];
        festival = new int[2];
        int test_case = Integer.parseInt(br.readLine());
        for(int test = 0; test< test_case; test++) {
            N = Integer.parseInt(br.readLine());
            spots = new ArrayList<>();
            String[] tmp;
            tmp = br.readLine().split(" ");
            // 집 위치 저장
            house[0] = Integer.parseInt(tmp[0]);
            house[1] = Integer.parseInt(tmp[1]);

            // 편의점 위치 저장
            for(int i=0; i<N; i++) {
                tmp = br.readLine().split(" ");
                spots.add(new Integer[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])});
            }

            tmp = br.readLine().split(" ");
            // 페스티벌 위치
            festival[0] = Integer.parseInt(tmp[0]);
            festival[1] = Integer.parseInt(tmp[1]);
            spots.add(new Integer[]{festival[0],festival[1]});

            boolean check = solve();
            if(check) System.out.println("happy");
            else System.out.println("sad");
        }
    }

    private static boolean solve() {
        int x = house[0];
        int y = house[1];
        // 편의점 안들리고 바로 갈 수 있는지 체크
        int distance = Math.abs(x-festival[0]) + Math.abs(y-festival[1]);
        if(distance <= 20 * 50) {
            return true;
        }
        
        // 편의점 들리면서 가자
        Queue<Integer[]> q= new LinkedList<>();
        q.add(new Integer[]{x,y});
        boolean[] visited = new boolean[spots.size()];

        while(!q.isEmpty()) {
            Integer[] xy = q.poll();
            x = xy[0];
            y = xy[1];
            if(x == festival[0] && y == festival[1]) {
                // 페스티벌 도착
                return true;
            }
            for(int i=0; i<spots.size(); i++) {
                if(visited[i]) continue;
                distance = Math.abs(x-spots.get(i)[0]) + Math.abs(y-spots.get(i)[1]);
                if(distance <= 20 * 50) {
                    // 안 들렸는 편의점 중에 거리 만족시키는 곳 가자
                    q.add(new Integer[]{spots.get(i)[0], spots.get(i)[1]});
                    visited[i] = true;
                }
            }
        }
        // 도착 못 함
        return false;
    }
}