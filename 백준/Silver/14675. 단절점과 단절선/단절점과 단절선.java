import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, q;
    private static Map<Integer, List<Integer>> tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new HashMap<Integer,List<Integer>>();
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            List<Integer> tmpList = new ArrayList<>();
            if(!tree.containsKey(a)) {
                tmpList.add(b);
                tree.put(a, tmpList);
            } else {
                tmpList = tree.get(a);
                tmpList.add(b);
                tree.put(a, tmpList);
            }
            tmpList = new ArrayList<>();
            if(!tree.containsKey(b)) {
                tmpList.add(a);
                tree.put(b, tmpList);
            } else {
                tmpList = tree.get(b);
                tmpList.add(a);
                tree.put(b, tmpList);
            }
        }

        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            List<Integer> nodeList = tree.get(k);
            if(t== 1) {
                if(nodeList.size() > 1 ) {
                    sb.append("yes");
                } else {
                    sb.append("no");
                }
            } else {
                sb.append("yes");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}