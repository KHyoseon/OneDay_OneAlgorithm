import java.util.*;

class Solution {
    char[][] map;
    int[] S, E, L;
    int N, M;
    boolean[][][] visited; // visited[][][] = r, c, 레버작동 OX
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        visited = new boolean[N][M][2];
        
        for(int i=0; i<N; i++) {
            map[i] = maps[i].toCharArray();
            if(maps[i].contains("S")) S = new int[]{i, maps[i].indexOf('S')};
            if(maps[i].contains("E")) E = new int[]{i, maps[i].indexOf('E')};
            if(maps[i].contains("L")) L = new int[]{i, maps[i].indexOf('L')};
        }
        
        // System.out.println("start: "+ Arrays.toString(S));
        // System.out.println("exit: "+ Arrays.toString(E));
        // System.out.println("lever: "+ Arrays.toString(L));
        
        // bfs
        int answer = bfs(S[0], S[1], 0);
        return answer;
    }
    
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int bfs(int r, int c, int key) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();

        queue.add(new int[]{r, c, key});
        visited[r][c][key] = true;

        int depth = 0;
        int nr, nc, nkey;
        while(!queue.isEmpty()) {
            ++depth;
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for(int d=0; d<4; d++) {
                    nr = cur[0] + dr[d];
                    nc = cur[1] + dc[d];
                    nkey = cur[2];

                    if(0>nr || 0>nc || N<=nr || M<=nc
                            || map[nr][nc] == 'X') continue;

                    if(nkey==1 && map[nr][nc] == 'E') return depth;
                    if(map[nr][nc] == 'L') nkey = 1;
                    if(visited[nr][nc][nkey]) continue;
                    visited[nr][nc][nkey] = true;
                    queue2.add(new int[]{nr, nc, nkey});
                }
            }
            queue.addAll(queue2);
            queue2.clear();
        }
        return -1;
    }
}