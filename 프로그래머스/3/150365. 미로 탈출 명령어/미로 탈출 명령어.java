import java.util.*;

class Solution {    
    public int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = manhattan(x, y, r, c);
        
        if(dist > k || ((k-dist) % 2) == 1)
            return "impossible";
        
        dfs(n, m, x-1, y-1, r-1, c-1, k, "");
        
        return answer;
    }
    
    String answer = "impossible";
    
    char[] move = {'d', 'l', 'r', 'u'};
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    
    public void dfs(int n, int m, int x, int y, int r, int c, int k, String path) {
        // 답이 나왔을 경우 종료
        if(!answer.equals("impossible"))
            return;
        
        // System.out.printf("cur: (%d, %d), path: %s\n", x, y, path);
        
        // 이동 다 했을 때 E에 도달하는가
        if(path.length() == k) {
            if(x == r && y == c)
                answer = path;
            return;
        }
        
        // 사전 순서대로 이동
        for(int d=0; d<4; d++) {
            int nx = x + dr[d];
            int ny = y + dc[d];
            
            // 이동 가능한 좌표?
            if(nx<0 || ny<0 || n<=nx || m<=ny) continue;
            
            // nx, ny에서 남은 이동횟수(k-path.length())만에 E에 도달가능?
            int left = k-path.length();
            int dist = manhattan(nx, ny, r, c);
            if(left < dist) continue;
            
            // 다 가능하면 탐색
            dfs(n, m, nx, ny, r, c, k, path + move[d]);
        }
        
    }
}