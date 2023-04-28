import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[50];
        dp[1] = dp[2] = 1;
        System.out.println(fibonacci(N));
    }

    private static long fibonacci(int n) {
        if(n<=0) return 0;
        if(dp[n] != 0) return dp[n];
        return dp[n] = (fibonacci(n - 1) + fibonacci(n - 2));
    }
}