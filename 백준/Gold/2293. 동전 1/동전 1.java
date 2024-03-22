import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int[] coins = new int[N+1];
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
            dp[i][0] = 1;
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                if (coins[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
            }
        }

        System.out.println(dp[N][K]);
    }

}