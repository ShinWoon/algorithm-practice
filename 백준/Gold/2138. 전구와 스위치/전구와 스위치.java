import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N, clickCnt;
    private static int[] start, result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        start = new int[N];
        String inputStr = br.readLine();
        for(int i=0; i<N; i++) {
            start[i] = Integer.parseInt(String.valueOf(inputStr.charAt(i)));
        }

        result = new int[N];
        inputStr = br.readLine();
        for(int i=0; i<N; i++) {
            result[i] = Integer.parseInt(String.valueOf(inputStr.charAt(i)));
        }

        solve();
    }

    private static void solve() {
        int[] tmpArr = new int[N];
        for(int i=0; i<N; i++) tmpArr[i] = start[i];
        int firstClicked = click(0, tmpArr);

        tmpArr = new int[N];
        for(int i=0; i<N; i++) tmpArr[i] = start[i];
        int firstNClicked = click(1, tmpArr);

        if(firstClicked == -1 && firstNClicked == -1) { // 만약 어느한 방법도 할 수 없으면
            System.out.println(-1);
        } else if(firstClicked == -1) {
            System.out.println(firstNClicked);
        } else if(firstNClicked == -1) {
            System.out.println(firstClicked);
        } else {    // 만약에 둘 다 가능한 경우 최소값 출력
            System.out.println(Math.min(firstClicked, firstNClicked));
        }
    }

    private static int click(int startIdx, int[] arr) {
        int count = 0;
        // 1번 스위치 눌렀을 때 1번 스위치 눌러주기

//        for(int i=0; i<N; i++) System.out.print(arr[i] + " ");
//        System.out.println();

        if(startIdx == 0) {
            arr = changeState(0, arr);
            arr = changeState(1, arr);
            count++;
        }
        // 1번 스위치 안누르고 시작
//        for(int i=0; i<N; i++) System.out.print(arr[i] + " ");
//        System.out.println();

        // 1번 스위치 이후 과정
        // 이번꺼랑 뒤에꺼 결과랑 다른지 아닌지 판단
        // 만약에 뒤에꺼 결과랑 다르다면 click

        for(int i=1; i<N; i++) {
            if(arr[i-1] == result[i-1]) continue;
            arr = changeState(i-1, arr);
            arr = changeState(i, arr);
            arr = changeState(i+1, arr);
            count++;
        }

//        System.out.println(count);

        // 원하는 결론값과 만족하는지 확인
        boolean check = true;
        for(int i=0; i<N; i++) if(arr[i] != result[i]) check = false;

        // 만약에 만족하지 못하면 -1을 리턴 아니면 횟수 리턴
        if (!check) return -1;
        else return count;
    }

    // 스위치 켜고 끄는 함수
    private static int[] changeState(int idx, int[] arr) {
        if(idx < N) {
            if(arr[idx] == 0) arr[idx] = 1;
            else arr[idx] = 0;
        }
        return arr;
    }
}