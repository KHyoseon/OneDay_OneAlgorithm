import java.util.*;
class Solution {
    int M, N, S;
    int[][] time_map;
    int[][] record;
    int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int[] solution(int M, int N, int S, int[][] time_map) {
        this.M = M;
        this.N = N;
        this.S = S;
        this.time_map = time_map;

        record = new int[M][N];
        for(int k=0; k<M; k++) {
                Arrays.fill(record[k], Integer.MAX_VALUE);
        }

        boolean[][] updated = new boolean[M][N];

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(time_map[i][j] <= 0){
                    record[i][j] = 0;
                }
            }
        }

        int moveDistance = Integer.MAX_VALUE;
        int sumOfTalkTime = Integer.MAX_VALUE;
        boolean done = false;

        int[] cur;
        int nr, nc, ns, depth;

        Queue<int[]> queue = new LinkedList<>();
        updated[0][0] = updated[M-1][N-1] = true;
        queue.add(new int[]{0, 0, 1});

        while (!done && !queue.isEmpty()) {
            cur = queue.poll();
            if(!updated[cur[0]][cur[1]]) continue;
            updated[cur[0]][cur[1]] = false;

            for (int d = 0; d < 4; d++) {
                nr = cur[0] + move[d][0];
                nc = cur[1] + move[d][1];
                depth = cur[2];

                if (!check(nr, nc, record[cur[0]][cur[1]])) continue;

                ns = record[cur[0]][cur[1]] + time_map[nr][nc];
                if (nr == M - 1 && nc == N - 1) {
                    if(moveDistance < depth) break;
                    moveDistance = depth;
                    sumOfTalkTime = Math.min(sumOfTalkTime, record[cur[0]][cur[1]]);
                }

                if(record[nr][nc] > ns) {
                    record[nr][nc] = ns;
                    updated[nr][nc] = true;
                }
                queue.add(new int[]{nr, nc, depth + 1});
            }
        }

        return new int[]{moveDistance, sumOfTalkTime};
    }

    public boolean check(int r, int c, int time) {
        if (r == M - 1 && c == N - 1) return true;
        // 범위 밖이면 안됨
        if (r < 0 || c < 0 || M <= r || N <= c) return false;
        if (time_map[r][c] == -1) return false;
        // 미친 오리되면 안됨
        if (time_map[r][c] + time < 0) return false;
        if (time + time_map[r][c] > S) return false;
        // 더 빠른 루트로 올 수 있었으면 안됨
        if (record[r][c] < time_map[r][c] + time) return false;
        return true;
    }
}