import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;

        int[] homMark = null;
        int homCount = 0;

        // 자물쇠 첫단추
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(lock[r][c]==0) { // 홈이면
                    homMark = new int[]{r, c};
                    homCount++;
                }
            }
        }
        if(homMark == null) return true;

        // 열쇠 돌기 저장
        ArrayList<int[]> dols = new ArrayList<>();
        for(int r=0; r<M; r++) {
            for(int c=0; c<M; c++) {
                if(key[r][c]==1) { // 돌기면
                    dols.add(new int[]{r, c});
                }
            }
        }

        if(dols.size() < homCount) return false;
        if(dols.size() + homCount == 0) return true;

        // 열쇠 회전 방향
        for(int t=0; t<4; t++) {
            // 위치 조정
            for(int[] dol: dols) {
                int row = homMark[0] - dol[0];
                int col = homMark[1] - dol[1];

                boolean answer = true;
                int cnt = homCount;

                for(int[] dol2: dols) {
                    int nr = dol2[0] + row;
                    int nc = dol2[1] + col;

                    // 자물쇠 밖으로 나가면 상관없음
                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;

                    // 자물쇠 돌기와 충돌하면 안됨
                    if(lock[nr][nc] == 1) {
                        answer = false;
                        break;
                    }
                    // 자물쇠 홈과 닿음
                    --cnt;
                }

                // 성공이면 종료
                if(answer && cnt==0) return true;
            }

            // 열쇠 돌림
            for(int[] dol: dols) {
                int temp = dol[0];
                dol[0] = dol[1];
                dol[1] = M - 1 - temp;
                // System.out.printf("(%d, %d), ", dol[0], dol[1]);
            }
        }

        return false;
    }
}