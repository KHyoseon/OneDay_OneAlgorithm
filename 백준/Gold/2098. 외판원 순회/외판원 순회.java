import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] weight;
    static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N][N];
        memo = new long[N][1<<N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            Arrays.fill(memo[i], -1);
        }

        System.out.println(TSP(0, 1));
    }

    private static long TSP(int cur, int visited) {
        if(memo[cur][visited] != -1) return memo[cur][visited];

        if(visited == (1<<N) - 1) {
            return weight[cur][0] == 0 ? Long.MAX_VALUE: weight[cur][0];
        }

        memo[cur][visited] = Long.MAX_VALUE;

        for(int j=0; j<N; j++) {
            if((visited & 1<<j) != 0 || weight[cur][j] == 0) continue;
            long ret = TSP(j, visited | 1<<j);
            if(ret == Long.MAX_VALUE) continue;
            memo[cur][visited] = Math.min(memo[cur][visited], weight[cur][j] + ret);
        }
        
        return memo[cur][visited];
    }
}