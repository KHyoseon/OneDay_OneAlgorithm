import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_2156_포도주_시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[N];

        for(int i=0; i<N; i++)
            wine[i] = Integer.parseInt(br.readLine());

        int[][] memo = new int[3][N];

        memo[0][0] = 0;
        memo[1][0] = memo[2][0] = wine[0];

        for(int i=1; i<N; i++){
            memo[0][i] = max(memo[0][i-1], memo[1][i-1], memo[2][i-1]);
            memo[1][i] = memo[0][i - 1] + wine[i];
            memo[2][i] = memo[1][i - 1] + wine[i];
        }

        System.out.print(max(memo[0][N-1], memo[1][N-1], memo[2][N-1]));
    }

    private static int max(int a, int b, int c) {
        if(a >= b && a >= c) return a;
        if(b >= a && b >= c) return b;
        return c;
    }
}
