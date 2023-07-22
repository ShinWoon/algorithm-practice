import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int resultArbN;
    private static String num1, num2, resultRomeN;
    private static Map<String, Integer> romanMap;
    private static Map<Integer, String> arabicMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num1 = br.readLine();
        num2 = br.readLine();

        resultArbN = 0;
        resultRomeN = "";
        romanMap = new HashMap() {{
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
        }};
        arabicMap = new HashMap() {{
            put(1, "I");
            put(5, "V");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");
            put(4, "IV");
            put(9, "IX");
            put(40, "XL");
            put(90, "XC");
            put(400, "CD");
            put(900, "CM");
        }};

        solve();
        System.out.println(resultArbN);
        System.out.print(resultRomeN);
    }

    private static void solve() {
        int num1Arab = roman2Arabic(num1);
        int num2Arab = roman2Arabic(num2);

        int sum = num1Arab + num2Arab;
        resultArbN = sum;
        resultRomeN = arabic2Roman(sum);
    }

    private static int roman2Arabic(String romanNum) {
        int arabicNum = 0;
        int idx = 0;
        while (idx < romanNum.length()) {
            if (idx == romanNum.length() - 1) {
                arabicNum += romanMap.get(String.valueOf(romanNum.charAt(idx)));
                idx++;
                continue;
            }
            String checkNum = String.valueOf(romanNum.charAt(idx)) + romanNum.charAt(idx + 1);
//            System.out.println(checkNum);
            if (romanMap.containsKey(checkNum)) {
                arabicNum += romanMap.get(checkNum);
                idx += 2;
            } else {
                arabicNum += romanMap.get(String.valueOf(romanNum.charAt(idx)));
                idx++;
            }
//            System.out.println(arabicNum);
        }

//        System.out.println(arabicNum);
        return arabicNum;
    }

    private static String arabic2Roman(int arabicNum) {
        String romanNum = "";
        int len = String.valueOf(arabicNum).length();
        int idx = (int) Math.pow(10, len - 1);

        while (idx > 0) {
            int thisNum = arabicNum / idx * idx;
            if (arabicMap.containsKey(thisNum)) {
                romanNum += arabicMap.get(thisNum);
            } else {
                if (arabicNum/idx >= 5) {
                    romanNum += arabicMap.get(5*idx);
                    for (int i = 0; i < arabicNum / idx - 5; i++) romanNum += arabicMap.get(idx);
                } else {
                    for (int i = 0; i < arabicNum / idx; i++) romanNum += arabicMap.get(idx);
                }
            }

            arabicNum %= idx;
            idx /= 10;
        }

        return romanNum;
    }
}