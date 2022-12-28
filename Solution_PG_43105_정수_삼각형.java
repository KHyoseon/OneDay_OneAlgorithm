public class Solution_PG_43105_정수_삼각형 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println((new Solution()).solution(triangle));
    }

    static class Solution {

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

        /*
        public int solution(int[][] triangle) {
            for (int i = 1; i < triangle.length; i++) {
                triangle[i][0] += triangle[i-1][0];
                triangle[i][i] += triangle[i-1][i-1];
                for (int j = 1; j < i; j++)
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }

            return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
        }
        */
    }
}
