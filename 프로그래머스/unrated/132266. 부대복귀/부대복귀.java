import java.util.*;

class Solution {
private static Map<Integer, List<Integer>> roadInfo;
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        roadInfo = new HashMap<>();
        for(int i=1; i<=n; i++) roadInfo.put(i, new ArrayList<>());

        for(int i=0; i<roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];

            roadInfo.get(a).add(b);
            roadInfo.get(b).add(a);
        }

        int[] lengthArr = new int[n+1];
        for(int i=1; i<=n; i++) lengthArr[i] = Integer.MAX_VALUE;

//        for(int i=0; i<sources.length; i++) {
//            int start = sources[i];
//            visited = new boolean[n+1];
//            answer[i] = getLen(start, destination, lengthArr);
//        }

        int[] result = getLen(destination, lengthArr);
        for(int i=0; i<sources.length; i++) answer[i] = result[sources[i]];
        return answer;
    }

    private static int[] getLen(int destination, int[] lengthArr) {
        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];   // 길이 순
            }
        });
        lengthArr[destination] = 0;
        q.add(new Integer[]{destination, lengthArr[destination]});

        while(!q.isEmpty()) {
            Integer[] info = q.poll();
            int current = info[0];
            int currentLength = info[1];

            for(int i=0; i<roadInfo.get(current).size(); i++) {
                int next = roadInfo.get(current).get(i);
                if(lengthArr[next] > currentLength + 1) {
                    lengthArr[next] = currentLength + 1;
                    q.add(new Integer[]{next, lengthArr[next]});
                }
            }
        }
        for(int i=1; i<lengthArr.length; i++) {
            if(lengthArr[i] == Integer.MAX_VALUE) lengthArr[i] = -1;
        }
        return lengthArr;
    }
}