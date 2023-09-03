import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Node {
        boolean checkEnd;
        Map<Character, Node> childNode;

        public Node(boolean checkEnd, Map<Character, Node> childNode) {
            this.checkEnd = checkEnd;
            this.childNode = childNode;
        }
    }

    private static int N;
    private static List<String> phoneNumList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            phoneNumList = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                phoneNumList.add(br.readLine());
            }

            Collections.sort(phoneNumList);
            boolean result = solve();
            if(result) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    private static boolean solve() {
        Node rootNode = new Node(false, new HashMap<>());

        for(int i=0; i<N; i++) {
            String phoneNum = phoneNumList.get(i);
            Node thisNode = rootNode;
            for(int j=0; j<phoneNum.length(); j++) {
                char thisChar = phoneNum.charAt(j);
                if(!thisNode.childNode.containsKey(thisChar)) {
                    thisNode.childNode.put(thisChar, new Node(false, new HashMap<>()));
                }
                else {
                    if(thisNode.childNode.get(thisChar).checkEnd) {
                        // 끝이라면
                        return false;
                    }
                }
                thisNode = thisNode.childNode.get(thisChar);

                if(j == phoneNum.length()-1) {
                    // 마지막
                    thisNode.checkEnd = true;
                }
            }
        }
        return true;
    }
}