import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int test_case=0; test_case<t; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer>[] S = new ArrayList[N];
            Set<Integer> totalSet = new HashSet<>();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                S[i] = new ArrayList<>();
                for(int j=0; j<k; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    S[i].add(num);
                    totalSet.add(num);
                }
            }
            int result = 0;
            if(N > 1) {
                for(int number: totalSet) {
                    Set<Integer> tmpSet = new HashSet<>();
                    for(List<Integer> ls: S) {
                        if(!ls.contains(number)) tmpSet.addAll(ls);
                    }
                    result = Math.max(result, tmpSet.size());
                }
            }
            System.out.println(result);
        }
    }
}