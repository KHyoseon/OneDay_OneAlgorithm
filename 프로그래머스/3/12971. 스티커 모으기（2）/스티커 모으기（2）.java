import java.util.*;
class Solution {
    int n;
    int[] sticker;
    int[][] memo;

    public int solution(int[] sticker) {
        this.n = sticker.length;
        this.sticker = sticker;
        this.memo = new int[n][2];
        
        for(int i=0; i<n; i++)
            Arrays.fill(memo[i], -1);
        
        memo[n-1] = new int[]{0, sticker[n-1]};
        
        int ret1 = dp(0, 0);
        int ret2 = dp(0, 1);
        
        return Math.max(ret1, ret2);
    }
    
    private int dp(int i, int last) {
        if(i >= n) return 0;
        if(memo[i][last] != -1) return memo[i][last];
        
        if(i==0) {
            if(last == 0)
                return Math.max(dp(i+1, last), dp(i+2, last) + sticker[i]);
            else
                return dp(i+1, last);
        }
        memo[i][last] = Math.max(dp(i+1, last), 
                                        dp(i+2, last) + sticker[i]);
        //System.out.printf("%d ~ %d(%d): %d\n", i, n, last, memo[i][last]);
        return memo[i][last];
    }

}