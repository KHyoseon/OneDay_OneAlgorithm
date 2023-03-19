class Solution {
    static int R, C;
    static boolean[][] isSame;
    public int solution(int[][] beginning, int[][] target) {
        R = beginning.length;
        C = beginning[0].length;

        isSame = new boolean[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++) {
                isSame[i][j] = beginning[i][j] == target[i][j];
            }
        }

        return test();
    }

    private int test() {
        boolean[][] copy = new boolean[R][C];
        for(int r=0; r<R; r++)
            copy[r] = isSame[r].clone();

        int ret1, ret2;
        if(isSame[0][0]){
            // 행, 열 둘 다 안뒤집
            ret1 = go(0);

            for(int r=0; r<R; r++)
                isSame[r] = copy[r].clone();

            // 행, 열 둘 다 뒤집기
            flip(-1, 0);
            flip(0, -1);
            ret2 = go(2);
        } else {
            // 행만 뒤집기
            flip(0, -1);
            ret1 = go(1);

            for(int r=0; r<R; r++)
                isSame[r] = copy[r].clone();

            // 열만 뒤집기
            flip(-1, 0);
            ret2 = go(1);
        }

        if(ret1 == Integer.MAX_VALUE && ret2 == Integer.MAX_VALUE) return -1;
        return Math.min(ret1, ret2);
    }

    private int go(int init) {
        int cnt = init;

        for (int c = 1; c < C; c++) {
            if (isSame[0][c]) continue;
            ++cnt;
            flip(-1, c);
        }
        for (int r = 1; r < R; r++) {
            if (isSame[r][0]) continue;
            ++cnt;
            flip(r, -1);
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!isSame[r][c]) return Integer.MAX_VALUE;
            }
        }
        return cnt;
    }

    private void flip(int rr, int cc) {
        if(rr>=0) {  // 행을 바꿈
            for(int c=0; c<C; c++) {
                isSame[rr][c] = !isSame[rr][c];
            }
        } else {    // 열을 바꿈
            for(int r=0; r<R; r++) {
                isSame[r][cc] = !isSame[r][cc];
            }
        }
    }
}