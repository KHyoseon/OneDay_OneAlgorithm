import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K, INF = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int[] coins = new int[N];
        int[] money = new int[K + 1];

        for(int i=0; i<N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
        Arrays.fill(money, INF);

        if(coins[0] > K) {
            System.out.println(-1);
            return;
        }

        for(int coin: coins) {
            if(coin > K) break;
            money[coin] = 1;
        }

        money[0] = 0;
        for(int i=1; i<=K; i++) {
            for(int c=0; c<N; c++) {
                if(i-coins[c] < 1) break;
                money[i] = Math.min(money[i], money[i-coins[c]] + 1);
            }
        }
        System.out.println(money[K] == INF? -1: money[K]);
    }
}