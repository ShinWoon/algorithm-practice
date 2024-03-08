import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int K, N, BLANK;
    private static char[][] ladder;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        String resultLine = br.readLine();
        char[] participantResult = new char[K];
        for(int i=0; i<K; i++) participantResult[i] = resultLine.charAt(i);

        ladder = new char[N][K-1];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<K-1; j++) ladder[i][j] = input.charAt(j);
            if(ladder[i][0] == '?') BLANK = i;
        }

        char[] fromTop = topToBottom();
        char[] fromBottom = bottomToTop(participantResult);
        String answer = fillBlank(fromTop, fromBottom);
        System.out.println(answer);
    }

    private static char[] topToBottom() {
        char[] startArr = new char[K];
        for(int i=0; i<K; i++) startArr[i] = (char)(65 + i);

        for(int row=0; row<BLANK; row++) {
            char[] tmpArr = new char[K];
            for(int column=0; column<K; column++) {
                if(column == 0) {
                    if(ladder[row][column] == '-') {
                        tmpArr[column+1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                } else if(column == K-1) {
                    if(ladder[row][column-1] == '-') {
                        tmpArr[column-1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                } else {
                    if(ladder[row][column-1] == '-') {
                        tmpArr[column-1] = startArr[column];
                    } else if(ladder[row][column] == '-') {
                        tmpArr[column+1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                }
            }
            startArr = tmpArr.clone();
//            print(startArr);
        }

        return startArr;
    }
    private static char[] bottomToTop(char[] startArr) {
        for (int row=N-1; row>BLANK; row--) {
            char[] tmpArr = new char[K];
            for(int column=0; column<K; column++) {
                if(column == 0) {
                    if(ladder[row][column] == '-') {
                        tmpArr[column+1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                } else if(column == K-1) {
                    if(ladder[row][column-1] == '-') {
                        tmpArr[column-1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                } else {
                    if(ladder[row][column-1] == '-') {
                        tmpArr[column-1] = startArr[column];
                    } else if(ladder[row][column] == '-') {
                        tmpArr[column+1] = startArr[column];
                    } else {
                        tmpArr[column] = startArr[column];
                    }
                }
            }
            startArr = tmpArr.clone();
//            print(startArr);
        }
        return startArr;
    }

    private static String fillBlank(char[] fromTop, char[] fromBottom) {
        String answer = "";
        for(int i=0; i<K-1; i++) {
            if(fromTop[i] == fromBottom[i]){    // 동일할 때
                answer += "*";
            }
            else if((fromTop[i] == fromBottom[i+1]) && (fromTop[i+1] == fromBottom[i])){ // 서로 바꼈을 때
                answer += "-";
            }
            else if (i != 0 && (fromTop[i] == fromBottom[i-1])) {  // 이전에 애들이 바뀐 상태
                answer += "*";
            } else {
                answer = "";
                for(int j=0; j<K-1; j++) answer += "x";
                return answer;
            }
        }
        return answer;
    }
    private static void print(char[] arr) {
        for(int i=0; i<K; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}