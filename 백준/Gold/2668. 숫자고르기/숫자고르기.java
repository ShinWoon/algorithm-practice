import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean[] numVisited;
    static int[] arr;
    static List<Integer> result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(br.readLine());

        solve();
        System.out.println(result.size());
        Collections.sort(result);
        for(int num : result) System.out.println(num);
    }

    public static void solve() {
        numVisited = new boolean[N+1];
        result = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            if(!numVisited[i]){
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(i);
                checkNumber(arr[i], i, tmpList);
            }
        }
    }

    public static void checkNumber(int start, int end, List<Integer> tmpList) {
        if(start == end) {
            // tmplist에 있는 수 result에 넣고 visited도 처리해주기
//            System.out.println("here");
            setVisited(tmpList);
            result.addAll(tmpList);
            return;
        }
        if(!numVisited[start]){
            tmpList.add(start);
//            System.out.println(start);
            numVisited[start] = true;
            checkNumber(arr[start], end, tmpList);
            numVisited[start] = false;
        }
    }

    public static void setVisited(List<Integer> tmpList) {
        for(int i : tmpList) numVisited[i] = true;
    }
}