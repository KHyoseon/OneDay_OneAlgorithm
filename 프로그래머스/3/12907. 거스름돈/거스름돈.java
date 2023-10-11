class Solution {
    public int solution(int n, int[] money) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        
        int MOD = 1000000007;
        
        for(int coin: money) {
            for(int price=money[0]; price<n+1; price++) {
                if(price >= coin) {
                    dp[price] += dp[price - coin];
                    dp[price] %= MOD;
                }
            }
        }

        return dp[n] % MOD;
    }
}