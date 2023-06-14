import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static char[][] starMap;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        starMap = new char[N][2*N-1];
        setStarMap();
        makeTriangle(N, N-1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N*2-1; j++) {
                sb.append(starMap[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void makeTriangle(int row, int x, int y) {
        if(row == 3) {  // 삼각형 만들자
            printStar(x,y);
            return;
        }
        makeTriangle(row/2, x, y);
        makeTriangle(row/2, x-row/2, y+row/2);
        makeTriangle(row/2, x+row/2, y+row/2);
    }
    private static void printStar(int x,int y) {
        /**
         * 이거 저장하는 함수
         *          *
         *         * *
         *        *****
         */
        starMap[y][x] = '*';
        starMap[y+1][x-1] = '*';
        starMap[y+1][x+1] = '*';
        for(int i=-2; i<=2; i++) {
            starMap[y+2][x+i] = '*';
        }
    }
    private static void setStarMap() {  // 일단 다 space 값으로 초기화하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N*2-1; j++) {
                starMap[i][j] = ' ';
            }
        }
    }
}