import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int H, W;
    private static int[] map, startMaxH, endMaxH;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new int[W];
        for(int i=0; i<W; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        startMaxH = findMaxH(true);
        endMaxH = findMaxH(false);

        int answer = 0;
        answer = solve();
        System.out.println(answer);
    }

    private static int[] findMaxH(boolean front) {
        int[] heightArr = new int[W];

        if(front) {
            for(int i=1; i<W-1; i++) {
                for(int j=0; j<=i; j++) {
                    heightArr[i] = Math.max(heightArr[i], map[j]);
                }
            }
        } else {
            for(int i=W-2; i>0; i--) {
                for(int j=W-1; j>=i; j--) {
                    heightArr[i] = Math.max(heightArr[i], map[j]);
                }
            }
        }
        return heightArr;
    }

    private static int solve() {
        int cnt = 0;
        for(int i=1; i<W-1; i++) {
            cnt += Math.min(startMaxH[i], endMaxH[i]) - map[i];
        }
        return cnt;
    }
}