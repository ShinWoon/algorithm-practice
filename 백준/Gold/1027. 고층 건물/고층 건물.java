import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, MAXBUILDING;
    private static int[] buildings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        buildings = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        MAXBUILDING = 0;
        solution();
        System.out.println(MAXBUILDING);
    }
    private static void solution() {
        int cnt;
        for(int i=0; i<N; i++) {
            cnt = 0;
            for(int j=0; j<N; j++) {
                boolean check = true;
                if(i == j) continue;
                double a = (double) (buildings[i] - buildings[j])/(i-j);
                double b = buildings[i] - a * i;
                if(j < i) {
                    for(int k=j+1; k<i; k++) {
                        if(a*k + b <= buildings[k]) {
                            check = false;
                        }
                    }
                } else {
                    for (int k = i + 1; k < j; k++) {
                        if (a * k + b <= buildings[k]) {
                            check = false;
                        }
                    }
                }
                if(check) cnt++;
            }
            MAXBUILDING = Math.max(MAXBUILDING, cnt);
        }
    }
}