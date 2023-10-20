class Solution {
    int[] numbers;
    int N, cnt = 0, target;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return cnt;
    }
    
    public void dfs(int depth, int sum) {
        if(depth >= N) {
            if(sum == target) cnt++;
            return;
        }
        
        dfs(depth+1, sum-numbers[depth]);
        dfs(depth+1, sum+numbers[depth]);
    }
}