import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] triangle, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j=0; j<i+1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dp(0, 0));
    }

    private static int dp(int r, int c) {
        if(r==N) return 0;
        if(dp[r][c] != -1) return dp[r][c];

        return dp[r][c] = Math.max(dp(r+1, c), c+1<N? dp(r+1, c+1): 0) + triangle[r][c];
    }

}