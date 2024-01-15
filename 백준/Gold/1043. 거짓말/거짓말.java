import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, possibleCnt;
    private static boolean[] knowTruth, partyCan;
    private static int[] truth;
    private static int[][] partyParticipants;
    private static Map<Integer, List<Integer>> partyInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthKnowCnt = Integer.parseInt(st.nextToken());
        truth = new int[truthKnowCnt];
        knowTruth = new boolean[N + 1];
        for (int i = 0; i < truthKnowCnt; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
            knowTruth[truth[i]] = true;
        }

        partyParticipants = new int[M + 1][];
        partyInfo = new HashMap<>();
        for (int i = 1; i <= N; i++) partyInfo.put(i, new ArrayList<>());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int people = Integer.parseInt(st.nextToken());
            partyParticipants[i] = new int[people];
            for (int j = 0; j < people; j++) {
                int who = Integer.parseInt(st.nextToken());
                partyInfo.get(who).add(i);
                partyParticipants[i][j] = who;
            }
        }
        solve();
        System.out.println(possibleCnt);
    }

    private static void solve() {
        partyCan = new boolean[M + 1];
        Arrays.fill(partyCan, true);
        for (int i = 0; i < truth.length; i++) {
            int who = truth[i];
            for (int j = 0; j < partyInfo.get(who).size(); j++) {
//                System.out.println("who " + who + " party " + partyInfo.get(who).get(j));
                // 같이 참석한 사람 찾기
                // 그 사람이 참석한 파티도 다 거짓말 못하게
                setParty(partyInfo.get(who).get(j));
            }
        }

        for (int i = 1; i <= M; i++) {
            if(partyCan[i]) possibleCnt++;
        }

    }

    private static void setParty(int partyNum) {
        Queue<Integer> q = new LinkedList<>();
        q.add(partyNum);

        while (!q.isEmpty()) {
            partyNum = q.poll();
            partyCan[partyNum] = false;    // 거짓말 못하는 파티
//            System.out.println(partyNum);

            for (int i = 0; i < partyParticipants[partyNum].length; i++) {
                int participant = partyParticipants[partyNum][i];
//                System.out.println("participant " + participant);
                if (!knowTruth[participant]) {
                    knowTruth[participant] = true;
                    for (int j = 0; j < partyInfo.get(participant).size(); j++) {
                        int otherParty = partyInfo.get(participant).get(j);
//                        System.out.println("other party " + otherParty);
                        q.add(otherParty);
                    }
                }
            }
        }
    }
}