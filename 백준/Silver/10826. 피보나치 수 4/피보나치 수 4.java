import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        System.out.println(fibonacci4(N));
    }
    
    private static BigInteger fibonacci4(int n) {
        if (n <= 0) {
            return new BigInteger("0");
        } else if (n==1 || n==2) {
            return new BigInteger("1");
        } else {
            BigInteger prev1 = new BigInteger("0");
            BigInteger prev2 = new BigInteger("1");
            BigInteger current = new BigInteger("0");

            for (int i = 2; i <= n; i++) {
                current = prev1.add(prev2);
                prev1 = prev2;
                prev2 = current;
            }
            return current;
        }
    }
}