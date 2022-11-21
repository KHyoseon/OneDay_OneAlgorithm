import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if(N==1) System.out.println(1);
        else if(N==2) System.out.println(2);
        else {
            int[] memo = new int[N + 1];
            memo[1] = 1;
            memo[2] = 2;

            for (int i = 3; i < N + 1; i++)
                memo[i] = (memo[i - 1] + memo[i - 2]) % 10007;

            System.out.println(memo[N]);
        }
    }
}
