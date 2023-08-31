import java.util.*;

class Solution {
            private static class Info {
        String parent;
        int profit;
        public Info(String parent, int profit) {
            this.parent = parent;
            this.profit = profit;
        }
    }
    private static Map<String, Info> enrollMap;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        enrollMap = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            String name = enroll[i];
            String parentName = referral[i];
            enrollMap.put(name, new Info(parentName, 0));
        }

        for(int i=0; i<seller.length; i++) {
            shareProfit(seller[i], amount[i] * 100);
//            System.out.println("------------------");
//            for(int j=0; j<enroll.length; j++) {
//                System.out.print(enrollMap.get(enroll[j]).profit + " ");
//            }
        }

        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            answer[i] = enrollMap.get(enroll[i]).profit;
        }
        return answer;
    }

    private static void shareProfit(String sellerName, int profitAmount) {
        String currentName = sellerName;
        int myProfit = profitAmount;
        int theirProfit = 0;

        while(true) {
            if(currentName.equals("-")) break;
            theirProfit = myProfit/10;
            if(theirProfit != 0) {
                myProfit -= myProfit/10;
                enrollMap.get(currentName).profit += myProfit;
            } else {
                enrollMap.get(currentName).profit += myProfit;
                break;
            }
            currentName = enrollMap.get(currentName).parent;
            myProfit = theirProfit;
        }
    }
}