class Solution {

    static int[][] tri, memo;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        tri = triangle;

        memo = new int[tri.length][tri.length];
        for(int i=0; i<tri.length; i++)
            for(int j=0; j<=i; j++)
                memo[i][j] = -1;

        answer = dp(0, 0);
        return answer;
    }

    private int dp(int r, int c) {
        if(memo[r][c] != -1)    return memo[r][c];
        if(r == tri.length-1)   return tri[r][c];
        return memo[r][c] = tri[r][c] + Math.max(dp(r + 1, c), dp(r + 1, c + 1));
    }
}