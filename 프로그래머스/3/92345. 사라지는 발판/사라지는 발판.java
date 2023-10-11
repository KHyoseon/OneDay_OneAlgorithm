import java.util.*;
class Solution {
    int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int N, M;

        public int solution(int[][] board, int[] aloc, int[] bloc) {
            N = board.length;
            M = board[0].length;

            boolean[][] visited = new boolean[N][M];
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    visited[i][j] = board[i][j]!=1;
                }
            }

            return Math.abs(simulate(visited, aloc[0], aloc[1], bloc[0], bloc[1], 0));
        }

        public int simulate(boolean[][] board, int ax, int ay, int bx, int by, int depth) {
            int nx, ny;

            boolean[][] visited = new boolean[N][M];
            boolean canGo = false;
            for(int i=0; i<N; i++) {
                visited[i] = Arrays.copyOf(board[i], M);
            }

            int min= Integer.MIN_VALUE, max=0;
            if(depth%2 == 0) {
                for(int d=0; d<4; d++) {
                    nx = ax + move[d][0];
                    ny = ay + move[d][1];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny || visited[nx][ny]) continue;

                    canGo = true;
                    if(bx == ax && by == ay) return depth + 1;

                    visited[ax][ay] = true;
                    // 절댓값이 작은 음수가 제일 좋고 그 다음으로 절댓값이 큰 양수가 좋음
                    int ret = simulate(visited, nx, ny, bx, by, depth+1);
                    visited[ax][ay] = false;

                    if(ret < 0) {
                        min = Math.max(min, ret);
                    }
                    else if(ret > 0) {
                        max = Math.max(max, ret);
                    }
                }

                // 내가 짐
                if(!canGo) return -1*depth;

                // 다 양수만 들어왔다면 최대 양수 반환
                if(min == Integer.MIN_VALUE) return -1 * max;
                else return Math.abs(min);

            } else {
                for(int d=0; d<4; d++) {
                    nx = bx + move[d][0];
                    ny = by + move[d][1];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny || visited[nx][ny]) continue;

                    canGo = true;
                    // 내가 이김
                    if(bx == ax && by == ay) return depth + 1;

                    visited[bx][by] = true;
                    int ret = simulate(visited, ax, ay, nx, ny, depth+1);
                    visited[bx][by] = false;

                    if(ret < 0) {
                        min = Math.max(min, ret);
                    }
                    else if(ret > 0) {
                        max = Math.max(max, ret);
                    }
                }

                // 내가 짐
                if(!canGo) return -1*depth;

                // 다 양수만 들어왔다면 최대 양수 반환
                if(min == Integer.MIN_VALUE) return -1 *max;
                else return Math.abs(min);
            }
        }
}