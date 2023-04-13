import java.util.*;

class Solution {
    int N, M;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        int[] cur;
        int nr, nc;
        
        while(!queue.isEmpty()) {
            cur = queue.poll();
            
            for(int d=0; d<4; d++) {
                nr = cur[0] + dr[d];
                nc = cur[1] + dc[d];

                if(nr<0 || N<=nr || nc<0 || M<=nc) continue;
                if(maps[nr][nc] == 0 || visited[nr][nc]) continue;
                
                if(nr == N-1 && nc == M-1) return cur[2] + 1;
                
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc, cur[2] + 1});
            }
        }

        return -1;
    }
}