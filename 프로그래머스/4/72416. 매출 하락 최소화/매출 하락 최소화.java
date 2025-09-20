import java.util.ArrayList;

class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    int[][] dp;
    int[] sales;

    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        this.dp = new int[sales.length][2];

        // make graph
        for(int i=0, l=sales.length; i<l; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] link: links) {
            graph.get(link[0]-1).add(link[1]-1);
        }

        // dfs
        dfs(0);

        return Math.min(dp[0][0], dp[0][1]);
    }

    private void dfs(int leader) {
        // leaf 노드이면
        if(graph.get(leader).isEmpty()) {
            dp[leader][0] = 0;
            dp[leader][1] = sales[leader];
            return;
        }

        // minSum: 해당 팀원이 참석할 경우 / 안 할 경우 중 최솟값을 다 더한 값
        // min: 팀원 중 아무도 참석하지 않을 경우 [0] 빼고 [1] 더할 최솟값
        int minSum = 0, min = Integer.MAX_VALUE;
        for(int follower: graph.get(leader)) {
            dfs(follower);
            minSum += Math.min(dp[follower][0], dp[follower][1]);
            min = Math.min(min, dp[follower][1] - dp[follower][0]);
        }

        // 팀장이 참석하면 팀원은 상관 없음
        dp[leader][1] = sales[leader] + minSum;

        // 팀장 참석 안 함
        dp[leader][0] = minSum + Math.max(min, 0);
    }
}