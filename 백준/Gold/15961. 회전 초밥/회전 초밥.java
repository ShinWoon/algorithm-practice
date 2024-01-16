import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, d, k, c;
    private static int[] sushiNum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushiNum = new int[N];
        for(int i=0; i<N; i++) {
            sushiNum[i] = Integer.parseInt(br.readLine());
        }

        int maxTypeCnt = 0;
        int typeCnt = 0;
        int[] ate = new int[d+1];
        // 초기 K 길이만큼 큐에 넣고 갯수 확인
        for(int i=0; i<k; i++) {
            int sushi = sushiNum[i];
            if(ate[sushi] == 0) typeCnt++;
            ate[sushi] += 1;
        }
        // 만약에 내가 지금까지 먹은 초밥 중에 쿠폰으로 먹을 수 있는 애가 없다면 종류 추가
        if(ate[c] == 0) maxTypeCnt = typeCnt + 1;
        else maxTypeCnt = typeCnt;

        int start, end;
        for(int i=1; i<N; i++) {
            start = i;
            end = (start + (k-1))%N;    // 마지막 애부터 먹으면 처음에 있는 애들로 넘어가야 함. 원 형태이기 때문에
            int before = sushiNum[i-1];  // 제일 먼저 먹은 애
            ate[before] -= 1;
            if(ate[before] == 0) {
                // 얘를 빼도 있다면, 종류는 그대로. 아니라면 종류 수 빼기
                typeCnt -= 1;
            }
            int sushi = sushiNum[end];  // 이번에 먹을 초밥
            if(ate[sushi] == 0) typeCnt++;
            ate[sushi] += 1;
            if(ate[c] == 0) maxTypeCnt = Math.max(maxTypeCnt, typeCnt + 1);
            else maxTypeCnt = Math.max(maxTypeCnt, typeCnt);
        }
        System.out.println(maxTypeCnt);
    }
}