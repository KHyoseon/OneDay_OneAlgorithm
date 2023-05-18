import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BigInteger[][] dp = new BigInteger[101][51];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        System.out.println(nCr(N, M));
    }

    private static BigInteger nCr(int n, int r) {
        r = Math.min(r, n-r);
        if(r == 1) return new BigInteger(n+"");
        if(r == 0) return new BigInteger("1");
        if(dp[n][r] != null) return dp[n][r];
        return dp[n][r] = nCr(n-1, r).add(nCr(n-1, r-1));
    }
}