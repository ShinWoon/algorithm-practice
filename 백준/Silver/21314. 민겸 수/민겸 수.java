import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static String mNum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        mNum = br.readLine();

        // 최댓값, 최솟값 출력
        StringBuilder sb = new StringBuilder();
        sb.append(getMaxNum()).append("\n");
        sb.append(getMinNum());
        System.out.println(sb);
    }
    private static String getMaxNum() {
        String resultStr = "";
        int mCnt = 0;
        for(int i=0; i<mNum.length(); i++) {
            if(mNum.charAt(i) == 'M') {
                mCnt += 1;
            } else {
                // MK 묶기
                resultStr += "5";
                for(int j=0; j<mCnt; j++) {
                    resultStr += "0";
                }
                mCnt = 0;
            }
        }
        if(mCnt != 0) {
            for(int j=0; j<mCnt; j++) {
                resultStr += "1";
            }
        }
        return resultStr;
    }
    private static String getMinNum() {
        String resultStr = "";
        int mCnt = 0;
        for(int i=0; i<mNum.length(); i++) {
            if(mNum.charAt(i) == 'M') {
                if(mCnt == 0) {
                    resultStr += "1";
                } else {
                    resultStr += "0";
                }
                mCnt += 1;
            } else {
                // MK 묶지 못하게 하기
                resultStr += "5";
                mCnt = 0;
            }
        }
        return resultStr;
    }
}
