import java.util.*;

class Solution {
    int N, M;
    int[] oil;
    int[][] map;
    boolean[][] visited;
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        map = land;
        
        oil = new int[M];
        visited = new boolean[N][M];
        
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(visited[r][c] || map[r][c]==0) continue;
                bfs(r, c);
            }
        }
        
        int max = -1;
        for(int o: oil) {
            max = Math.max(max, o);
        }
        
        return max;
    }
    
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        
        visited[r][c] = true;
        queue.add(new int[]{r, c});
        
        int size = 0;
        HashSet<Integer> set = new HashSet<>();
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            size++;
            set.add(cur[1]);
            
            for(int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if(nr<0 || nc<0 || N<=nr || M<=nc) continue;
                if(map[nr][nc]==0 || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
        
        for(int col: set) {
            oil[col] += size;
        }
    }
}
