package code;

public class Solution_PG_131703_2차원_동전_뒤집기 {
    public static void main(String[] args) {
//        int[][] beginning = {{0, 1, 0, 0, 0},{1, 0, 1, 0, 1},{0, 1, 1, 1, 0},{1, 0, 1, 1, 0},{0, 1, 0, 1, 0}};
//        int[][] target = {{0, 0, 0, 1, 1},{0, 0, 0, 0, 1},{0, 0, 1, 0, 1},{0, 0, 0, 1, 0},{0, 0, 0, 0, 1}};
//        int[][] beginning = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
//        int[][] target = {{1, 0, 1},{0, 0, 0},{0, 0, 0}};
        int[][] beginning = {{0, 0, 0, 1, 1},{1, 1, 1, 1, 0},{1, 1, 0, 1, 0},{1, 1, 1, 0, 1},{1, 1, 1, 1, 0}};
        int[][] target = {{1, 0, 1, 0, 0},{1, 0, 1, 1, 0},{0, 1, 1, 0, 1},{1, 0, 1, 0, 1},{0, 1, 0, 0, 1}};
        System.out.println(new Solution_131703().solution(beginning, target));
    }

    static class Solution_131703 {
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
            if(isSame[0][0]){
                int ret = go();
                return ret== Integer.MAX_VALUE? -1: ret;
            }

            // 행으로 뒤집기
            boolean[][] copy = new boolean[R][C];
            for(int r=0; r<R; r++)
                copy[r] = isSame[r].clone();

            flip(0, -1);
            int ret1 = go();

            // 열로 뒤집기
            for(int r=0; r<R; r++)
                isSame[r] = copy[r].clone();

            flip(-1, 0);
            int ret2 = go();

            if(ret1 == Integer.MAX_VALUE && ret2 == Integer.MAX_VALUE) return -1;
            return Math.min(ret1, ret2) + 1;
        }

        private int go() {
            int cnt = 0;

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
}
