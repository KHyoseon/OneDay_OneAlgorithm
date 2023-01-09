class Solution {
    public int solution(int x, int y, int[][] puddles) {
        int[][] map = new int[y+1][x+1];
        boolean[][] skip = new boolean[y + 1][x + 1];

        for(int[] cord: puddles)
            skip[cord[1]][cord[0]] = true;
        
        for(int j=1; j<=x; j++){
            if(skip[1][j]) break;
            map[1][j] = 1;
        }
        for(int i=1; i<=y; i++){
            if(skip[i][1]) break;
            map[i][1] = 1;
        }

        final int MOD = 1000000007;
        for(int i=2; i<=y; i++){
            for(int j=2; j<=x; j++){
                if(skip[i][j]) map[i][j] = 0;
                else map[i][j] = (map[i - 1][j] + map[i][j - 1]) % MOD;
            }
        }

        return map[y][x];
    }
}