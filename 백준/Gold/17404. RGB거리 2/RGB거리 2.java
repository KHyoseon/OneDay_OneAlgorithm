import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, INF = 123456789, MIN = INF;
    static int[][] cost, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        simulate(0);
        simulate(1);
        simulate(2);

        System.out.println(MIN);
    }

    private static void simulate(int color) {
        dp[0][color] = cost[0][color];
        dp[0][(color+1)%3] = dp[0][(color+2)%3] = INF;

        for(int i=1; i<N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }
        
        dp[N-1][color] = INF;
        MIN = Math.min(MIN, Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));
    }

}