class Solution {
    private static int N, M, spaceCnt;
    private static int[][] keyArr, lockArr;
    private static boolean answer;
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        spaceCnt = 0;
        keyArr = new int[M][M];
        lockArr = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                lockArr[i][j] = lock[i][j];
                if(lockArr[i][j] == 0) spaceCnt++;
            }
        }
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                keyArr[i][j] = key[i][j];
            }
        }
        answer = false;
        solve();
        return answer;
    }
    
    private static void solve() {
        int[][] thisKey = new int[M][M];
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                thisKey[i][j] = keyArr[i][j];
            }
        }
        for(int i=0; i<4; i++) {
            if(checkKey(thisKey)) {
                answer = true;
                break;
            }   
            thisKey = rotate(thisKey);
        }
    }
    private static int[][] rotate(int[][] thisKey) {
        int[][] rotatedKey = new int[M][M];
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                rotatedKey[j][M-1-i] = thisKey[i][j];
            }
        }
        return rotatedKey;
    }
//     private static void dfs(int[][] thisKey, int turn) {
//         if(answer) return;
//         if(checkKey(thisKey)) {
//             answer = true;
//             return;
//         }
//         if(turn == 4) return;
//         int[][] changedKey = new int[M][M];
        
        
//     }
    private static boolean checkKey(int[][] thisKey) {
        int cnt = 0;
        boolean check = true;
        for(int i=-(M-1); i<N; i++) {
            for(int j=-(M-1); j<N; j++) {
                check = true;
                cnt = 0;
                for(int k=0; k<M; k++) {
                    for(int l=0; l<M; l++) {
                        if(i+k>=N || i+k<0 || j+l>=N || j+l<0) continue;
                        if(lockArr[i+k][j+l] == 0 && thisKey[k][l] == 1) {
                            cnt++;
                        }
                        if(lockArr[i+k][j+l] == 1 && thisKey[k][l] == 1) {
                            check = false;
                            break;
                        }
                    }
                }
                if(check && cnt == spaceCnt) return true;
            }
        }
        return false;
    }
}