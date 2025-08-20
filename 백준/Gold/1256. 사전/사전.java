import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        comb = new int[201][101];
        for(int i=0; i<101; i++)
            comb[i][0] = 1;
        for(int i=0; i<51; i++)
            comb[0][i] = 1;

        if(nCr(N+M, N) < K) {
            System.out.println("-1");
            return;
        }

        System.out.println(func(N, M, K, 0, ""));
    }

    private static String func(int n, int m, int K, int lower, String str) {
        if(n == 0 || m == 0){
            for (; 0 < n; n--)
                str += "a";
            for (; 0 < m; m--)
                str += "z";
            return str;
        }

        // 현재 자리에 a를 넣었을 때 남은 a, z를 배열할 수 있는 경우의 수 -> nCr(n+m-1, m)
        int upper = nCr(n+m-1, m);
        if(K <= lower + upper)
            return func(n-1, m, K, lower, str + "a");
        return func(n, m-1, K, lower + upper, str + "z");
    }

    static int nCr(int n, int r) {
        r = Math.min(r, n-r);
        if(comb[n][r] != 0) return comb[n][r];
        return comb[n][r] = Math.min((int)1e9+1, nCr(n-1, r) + nCr(n-1, r-1));
    }

}