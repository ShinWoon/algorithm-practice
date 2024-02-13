import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        int result = 0;
        for(int i=0; i<N-1; i++) {
            String word1 = words[i];
            for(int j=i+1; j<N; j++) {
                String word2 = words[j];

                boolean flag = true;
                char[] alphabet = new char[26];
                Arrays.fill(alphabet, ' ');
                boolean[] visited = new boolean[26];

                for(int k=0; k<word1.length(); k++) {
                    int idx1 = word1.charAt(k) - 'a';
                    int idx2 = word2.charAt(k) - 'a';

                    if(alphabet[idx1] == ' ') {
                        if(!visited[idx2]) {
                            alphabet[idx1] = word2.charAt(k);
                            visited[idx2] = true;
                        } else {
                            flag = false;
                            break;
                        }
                    } else if(alphabet[idx1] != word2.charAt(k)) {
                        // 원래 다시 그 값이 나와야하는데 안나온거니까 불가능
                        flag = false;
                        break;
                    }
                }
                if(flag) result++;
            }
        }
        System.out.println(result);
    }
}