import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static int S, result;
    private static boolean[] screenVisited, clipVisited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        result = Integer.MAX_VALUE;
        solve();
        System.out.println(result);
    }

    private static void solve() {
        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if(Objects.equals(o1[2], o2[2])) {
                    return o1[0] - o2[0];
                }
                return o1[2] - o2[2];   // 시간이 적은 수
            }
        });
        q.add(new Integer[]{1, 0, 0}); // 화면에 있는 이모티콘 수, 클립보드에 있는 이모티콘 수, 걸린 시간
        screenVisited = new boolean[10000];
        clipVisited = new boolean[10000];

        while (!q.isEmpty()) {
            Integer[] info = q.poll();
            int screenEmoji = info[0];
            int clipEmoji = info[1];
            int time = info[2];

            if (screenEmoji == S) {
                result = Math.min(result, time);
                return;
            }
            screenVisited[screenEmoji] = true;
            clipVisited[clipEmoji] = true;

            // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
            if (!screenVisited[screenEmoji] || !clipVisited[screenEmoji])
                q.add(new Integer[]{screenEmoji, screenEmoji, time + 1});

            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
            if (!screenVisited[screenEmoji + clipEmoji] || !clipVisited[clipEmoji])
                q.add(new Integer[]{screenEmoji + clipEmoji, clipEmoji, time + 1});

            // 화면에 있는 이모티콘 중 하나를 삭제
            if (screenEmoji-1 > 0 && (!screenVisited[screenEmoji - 1] || !clipVisited[clipEmoji]))
                q.add(new Integer[]{screenEmoji - 1, clipEmoji, time + 1});
        }
    }
}