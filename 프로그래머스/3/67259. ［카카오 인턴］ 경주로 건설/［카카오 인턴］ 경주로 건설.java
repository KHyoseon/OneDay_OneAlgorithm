import java.util.*;
class Solution {
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int N = board.length;
        int[][][] costs = new int[N][N][4];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(costs[i][j], Integer.MAX_VALUE);
            }
        }

        // {r, c, direction, cost}
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[3]-o2[3]);

        queue.add(new int[]{0, 0, 0, 0});
        queue.add(new int[]{0, 0, 1, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0]==N-1 && cur[1]==N-1) break;

            for(int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(nr<0 || nc<0 || N<=nr || N<=nc) continue;
                if(board[nr][nc] == 1) continue;

                int cost = cur[3] + 100 + (d == cur[2] ? 0 : 500);

                if(costs[nr][nc][d] < cost) continue;

                costs[nr][nc][d] = cost;
                queue.add(new int[]{nr, nc, d, cost});
            }
        }
        
        return Arrays.stream(costs[N - 1][N - 1]).min().getAsInt();
    }
}