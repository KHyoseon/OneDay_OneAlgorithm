import java.util.*;

class Solution {
    int[] nums;
    int[][] weight = new int[11][11];
    int[][][] dp = new int[100001][11][11];

    public int solution(String numbers) {
        /*
        9번 인덱스 제외 0~8 + 10(0) 이용
        0 1 2
        3 4 5
        6 7 8
          10
         */
        nums = new int[numbers.length()];
        int idx=0;
        for(char n: numbers.toCharArray()) {
            if(n == '0') nums[idx++] = 10;
            else nums[idx++] = n - '0' -1;
        }

        createWeightArray();

        /*
        아이디어: index+1 ~ number.length()-1 를 모두 누른 최솟값을 알고 있다면 index에서 왼손을 움직일지 오른손을 움직일지만 결정하면 됨
        sol(index, l, r): 양손이 (l, r) 위치에 있을 때 index 번째 번호(=n)를 누를 경우 가능한 최소 시간
        == sol(index+1, n, r) + weight[l][n] 과 sol(index+1, l, n) + weight[r][n] 중 작은 값을 고름
        */
        return sol(0, 3, 5);
    }

    private int sol(int index, int left, int right) {
        if(index == nums.length) return 0;
        if(dp[index][left][right] != 0) return dp[index][left][right];

        int target = nums[index];
        int answer = Integer.MAX_VALUE;

        // left
        if(target != right)
            answer = Math.min(answer, sol(index+1, target, right) + weight[left][target]);
        // right
        if(target != left)
            answer = Math.min(answer, sol(index+1, left, target) + weight[right][target]);

        return dp[index][left][right] = answer;
    }

    private void createWeightArray() {
        for(int i=0; i<11; i++) {
            if(i == 9) continue;
            for(int j=0; j<=i; j++) {
                if(j == 9) continue;
                weight[i][j] = weight[j][i] = getDist(i, j);
            }
        }
    }

    public int getDist(int n1, int n2) {
        if(n1 == n2) return 1;

        int[] mht = new int[]{Math.abs(n1/3 - n2/3), Math.abs(n1%3 - n2%3)};
        int cross = Math.min(mht[0], mht[1]);
        int straight = Math.max(mht[0], mht[1]) - cross;

        return 3*cross + 2*straight;
    }
}