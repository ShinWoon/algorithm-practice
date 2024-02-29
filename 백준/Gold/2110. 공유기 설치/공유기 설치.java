import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, C;
    private static int[] houses;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for(int i=0; i<N; i++) houses[i] = Integer.parseInt(br.readLine());

        // 집 위치 정렬
        Arrays.sort(houses);

        int start = 1;  // 거리의 최소 크기
        int end = houses[N-1] - houses[0] + 1;  // 거리의 최대 크기
        // 거리의 값을 조절하며 몇개의 집에 설치할 수 있는지 확인 -> C 값 만족하는 최대 길이 찾아보기
        while (start < end) {
            int middle = (start + end) / 2;
            int checkedC = checkCnt(middle);
            if(checkedC < C) {  // 적다 그러면 길이를 줄여야 한다
                end = middle;
            } else {    // 길이를 한번 늘려보자
                start = middle + 1;
            }
        }
        System.out.println(start - 1);
    }
    private static int checkCnt(int len) {
        int cnt = 1;    // 일단 제일 앞에 있는 애는 설치했다고 가정
        int beforeIdx = 0;
        for(int i=1; i<N; i++) {
            if(houses[i] - houses[beforeIdx] >= len) {  // 공유기 사이 거리가 내가 알아보는 거리 보다 크거나 같으면 설치할 수 있음
                cnt++;
                beforeIdx = i;  // 이제 최근에 설치한 애 위치 갱신
            }
        }
        return cnt;
    }
}