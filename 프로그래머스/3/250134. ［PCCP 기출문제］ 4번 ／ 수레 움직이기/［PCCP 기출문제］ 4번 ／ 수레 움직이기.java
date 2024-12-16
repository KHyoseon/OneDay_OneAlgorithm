class Solution {
    int N, M;
    int[][] maze;
    Wagon[] wagons = new Wagon[2];

    boolean[][][] visited;

    public int solution(int[][] maze) {
        this.maze = maze;

        // 1: R_start, 2: B_start, 3: R_end, 4: B_end

        N = maze.length;
        M = maze[0].length;

        visited = new boolean[N][M][2];

        wagons[0] = new Wagon();
        wagons[1] = new Wagon();

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                switch (maze[r][c]) {
                    case 1:
                        wagons[0].cur = new int[]{r, c};
                        visited[r][c][0] = true;
                        break;
                    case 2:
                        wagons[1].cur = new int[]{r, c};
                        visited[r][c][1] = true;
                        break;
                    case 3:
                        wagons[0].dest = new int[]{r, c};
                        break;
                    case 4:
                        wagons[1].dest = new int[]{r, c};
                        break;
                }
            }
        }

        dfs(0);

        return MIN== Integer.MAX_VALUE? 0: MIN;
    }

    int MIN = Integer.MAX_VALUE;
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};

    private void dfs(int time) {
        if(time > MIN) return;

        int[] cur1 = wagons[0].cur;
        int[] cur2 = wagons[1].cur;

        if(wagons[0].isArrived()) {
            // R 도착
            if (wagons[1].isArrived()) {
                // B 도착
                MIN = Math.min(MIN, time);
            } else {
                // B 도착 X
                for (int dd = 0; dd < 4; dd++) {
                    int nnr = cur2[0] + dr[dd];
                    int nnc = cur2[1] + dc[dd];

                    if (nnr < 0 || nnc < 0 || N <= nnr || M <= nnc) continue;
                    if (wagons[0].isAt(nnr, nnc) || visited[nnr][nnc][1] || maze[nnr][nnc] == 5) continue;

                    wagons[1].cur = new int[]{nnr, nnc};
                    visited[nnr][nnc][1] = true;
                    dfs(time + 1);
                    visited[nnr][nnc][1] = false;
                    wagons[1].cur = cur2;
                }
            }
        }
        else {
            // R 도착 X
            for(int d=0; d<4; d++) {
                int nr = cur1[0] + dr[d];
                int nc = cur1[1] + dc[d];

                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                if (visited[nr][nc][0] || maze[nr][nc] == 5) continue;

                if (wagons[1].isArrived()) {
                    // B 도착
                    if(wagons[1].isAt(nr, nc)) continue;

                    visited[nr][nc][0] = true;
                    wagons[0].cur = new int[]{nr, nc};

                    dfs(time + 1);

                    wagons[0].cur = cur1;
                    visited[nr][nc][0] = false;
                } else {
                    // B 도착 X
                    for (int dd = 0; dd < 4; dd++) {
                        int nnr = cur2[0] + dr[dd];
                        int nnc = cur2[1] + dc[dd];

                        if (nnr < 0 || nnc < 0 || N <= nnr || M <= nnc) continue;
                        if(visited[nnr][nnc][1] || maze[nnr][nnc] == 5) continue;

                        // *** trade ***
                        if (wagons[1].isAt(nr, nc) && wagons[0].isAt(nnr, nnc)) continue;
                        // *** crash ***
                        if (nr==nnr && nc==nnc) continue;

                        visited[nr][nc][0] = true;
                        wagons[0].cur = new int[]{nr, nc};

                        wagons[1].cur = new int[]{nnr, nnc};
                        visited[nnr][nnc][1] = true;
                        dfs(time + 1);
                        visited[nnr][nnc][1] = false;
                        wagons[1].cur = cur2;

                        wagons[0].cur = cur1;
                        visited[nr][nc][0] = false;
                    }
                }
            }
        }
    }


    class Wagon {
        int[] cur;
        int[] dest;

        public boolean isArrived() {
            return cur[0] == dest[0] && cur[1] == dest[1];
        }

        public boolean isAt(int nnr, int nnc) {
            return cur[0] == nnr && cur[1] == nnc;
        }
    }
}