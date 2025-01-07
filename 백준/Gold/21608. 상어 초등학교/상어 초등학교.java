import java.util.*;
import java.io.*;

public class Main {
    private static int N, result;
    private static int[] studentsOrder, dx = {0,-1,0,1}, dy = {-1,0,1,0}, statisfactionArray = {0,1,10,100,1000};
    private static int[][] map, friendsArray;
    private static boolean[] done;
    private static boolean[][] friendsBoolArray;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        studentsOrder = new int[N*N];
        map = new int[N+1][N+1];
        friendsArray = new int[N*N+1][4];
        done = new boolean[N*N+1];
        friendsBoolArray = new boolean[N*N+1][N*N+1];
        result = 0;

        // 친구 관계
        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            studentsOrder[i] = student;
            for(int j=0; j<4; j++) {
               int friend = Integer.parseInt(st.nextToken());
               friendsArray[student][j] = friend;
               friendsBoolArray[student][friend] = true;
            }
        }

        // 첫번째 학생 배치. 조건을 따르면 무조건 (2,2)에 배치(인접 칸 4개, 그 중 행, 열이 가장 적은 곳)
        map[2][2] = studentsOrder[0];
        done[studentsOrder[0]] = true;

        // 나머지 학생 배치
        for(int i=1; i<N*N; i++) {
            findSeatAndSit(studentsOrder[i]);
        }

        // printMap();

        // 만족도 합 구하기
        result = countSatisfaction();
        System.out.println(result);
    }

    /** 친구 주변 빈 곳 찾기 & 자리 잡기 */
    private static void findSeatAndSit(int student) {
        boolean isFriendIn = false;
        int friendCnt = 0;
        int emptySeat = 0;
        int seatR = N+1;
        int seatC = N+1;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(map[r][c] == 0) {
                    int cnt = 0;
                    boolean checkFriend = false;
                    int currentFriendCnt = 0;
                    // 주변에 친구가 있는지 확인
                    for(int k=0; k<4; k++) {
                        int cy = r + dy[k];
                        int cx = c + dx[k];
                        if(cx<=0 || cx>N || cy<=0 || cy>N) continue;
                        if(map[cy][cx] == 0){
                            cnt++;
                        } else {
                            if(friendsBoolArray[student][map[cy][cx]]) {
                                checkFriend = true;
                                currentFriendCnt++;
                            }
                        } 
                    }
                    if(!isFriendIn) {   // 친구가 없었을 때
                        if(checkFriend) {
                            isFriendIn = true;
                            friendCnt = currentFriendCnt;
                            emptySeat = cnt;
                            seatR = r;
                            seatC = c;
                        } else {
                            if(emptySeat < cnt) {
                                emptySeat = cnt;
                                seatR = r;
                                seatC = c;
                            } else if(emptySeat == cnt) {
                                if(r < seatR) {
                                    seatR = r;
                                    seatC = c;
                                } else if(r == seatR) {
                                    if(c < seatC) {
                                        seatR = r;
                                        seatC = c;
                                    }
                                }
                            }
                        }
                    } else {    // 친구 있는거 확인했었을 때
                        if(checkFriend) {
                            if(friendCnt < currentFriendCnt) {
                                friendCnt = currentFriendCnt;
                                emptySeat = cnt;
                                seatR = r;
                                seatC = c;
                            } else if(friendCnt == currentFriendCnt) {
                                if(emptySeat < cnt) {
                                    emptySeat = cnt;
                                    seatR = r;
                                    seatC = c;
                                }
                            }
                        
                        }
                    }
                }
            }
        }
        map[seatR][seatC] = student;
        done[student] = true;
    }

    /** 만족도 합 구하는 함수 */
    private static int countSatisfaction() {
        int cnt = 0;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                int student = map[r][c];
                int statisfactionSum = 0;
                for(int k=0; k<4; k++) {
                    int cx = c + dx[k];
                    int cy = r + dy[k];
                    if(cx<=0 || cx>N || cy<=0 || cy>N) continue;
                    if(friendsBoolArray[student][map[cy][cx]]) statisfactionSum++;
                }
                cnt += statisfactionArray[statisfactionSum];
            }
        }
        return cnt;
    }

    private static void printMap() {
        // 자리배치도 출력
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
