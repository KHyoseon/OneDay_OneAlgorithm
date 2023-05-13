package code;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_60063_블록_이동하기{
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(new Solution_60063().solution(board));
    }
}

class Solution_60063 {
    int N, MIN = Integer.MAX_VALUE;
    public int solution(int[][] board) {
        N = board.length;
//        bfs(board);
        visited = new boolean[N][N][4];
        bfs(new int[]{0,0,1,1,0}, board);
        return MIN;
    }

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][] rt = {{0,0,1,-1}, {1,1,0,0}, {-1,1,0,0}, {0,0,-1,-1}};
    boolean[][][] visited;

    public void bfs(int[] cur, int[][] board) {
        int r1, c1, r2, c2;
        if(cur[2]==N-1&&cur[3]==N-1){
            MIN = cur[4];
            return;
        }

        for(int ro=0; ro<4; ro++) {
            r1 = cur[0]+rt[ro][0];
            c1 = cur[1]+rt[ro][1];
            r2 = cur[2]+rt[ro][2];
            c2 = cur[3]+rt[ro][3];
            if(r1 > r2 || c1 > c2) {
                int tmp = r1;
                r1 = r2;
                r2 = tmp;
                tmp = c1;
                c1 = c2;
                c2 = tmp;
            }
            if(!visited[r1][] || !canRotate(board, r1, c1, r2, c2, ro)) continue;
            bfs(new int[]{r1, c1, r2, c2, cur[4]}, board);
        }
        for(int d=0; d<4; d++) {
            r1 = cur[0]+dr[d];
            c1 = cur[1]+dc[d];
            r2 = cur[2]+dr[d];
            c2 = cur[3]+dc[d];
            if(r1 > r2 || c1 > c2) {
                int tmp = r1;
                r1 = r2;
                r2 = tmp;
                tmp = c1;
                c1 = c2;
                c2 = tmp;
            }
            if(!canMove(board, r1, c1, r2, c2)) continue;
            bfs(new int[]{r1, c1, r2, c2, cur[4]+1}, board);
        }
    }
    public void bfs(int[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0,1,0});

        int[] cur;
        int r1, c1, r2, c2;
        while(!queue.isEmpty()) {
            cur = queue.poll();

            //System.out.println(Arrays.toString(cur));

            if(cur[2]==N-1&&cur[3]==N-1){
                MIN = cur[4];
                break;
            }

            for(int ro=0; ro<4; ro++) {
                r1 = cur[0]+rt[ro][0];
                c1 = cur[1]+rt[ro][1];
                r2 = cur[2]+rt[ro][2];
                c2 = cur[3]+rt[ro][3];
                if(r1 > r2 || c1 > c2) {
                    int tmp = r1;
                    r1 = r2;
                    r2 = tmp;
                    tmp = c1;
                    c1 = c2;
                    c2 = tmp;
                }
                if(!canRotate(board, r1, c1, r2, c2, ro)) continue;
                queue.add(new int[]{r1, c1, r2, c2, cur[4]});
            }
            for(int d=0; d<4; d++) {
                r1 = cur[0]+dr[d];
                c1 = cur[1]+dc[d];
                r2 = cur[2]+dr[d];
                c2 = cur[3]+dc[d];
                if(r1 > r2 || c1 > c2) {
                    int tmp = r1;
                    r1 = r2;
                    r2 = tmp;
                    tmp = c1;
                    c1 = c2;
                    c2 = tmp;
                }
                if(!canMove(board, r1, c1, r2, c2)) continue;
                queue.add(new int[]{r1, c1, r2, c2, cur[4]+1});
            }
        }
    }

    public boolean canRotate(int[][] board, int r1, int c1, int r2, int c2, int direct){
        if(!canMove(board, r1, c1, r2, c2)) return false;
        switch(direct) {
            case 0:
                if(board[r2-1][c2+1] == 1) return false;
                break;
            case 1:
                if(board[r1-1][c1-1] == 1) return false;
                break;
            case 2:
                if(board[r1+1][c1-1] == 1) return false;
                break;
            case 3:
                if(board[r2+1][c2+1] == 1) return false;
                break;
        }
        return true;
    }

    public boolean canMove(int[][] board, int r1, int c1, int r2, int c2){
        if(r1<0 || c1<0 || N<=r1 || N<=c1 || board[r1][c1] == 1) return false;
        if(r2<0 || c2<0 || N<=r2 || N<=c2 || board[r2][c2] == 1) return false;
        return true;
    }
}
