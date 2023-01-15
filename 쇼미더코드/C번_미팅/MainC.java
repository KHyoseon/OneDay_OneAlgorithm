import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainC {
  // 시간초과 실패
  
    static int N, M, C;
    static int[][] W;
    static int[] A, B;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[M];

        W = new int[C][C];
        for(int i=0; i<C; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                W[i][j] = W[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int a=0; a<N; a++)
            A[a] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int b=0; b<M; b++)
            B[b] = Integer.parseInt(st.nextToken());

        if(N > M){
            int[] tmp = Arrays.copyOf(B, M);
            B = Arrays.copyOf(A, N);
            A = Arrays.copyOf(tmp, M);

            int temp = M;
            M = N;
            N = temp;
        }

        dp = new int[N][M];
        go(-1, -1, 0);

        System.out.println(MAX);
    }
    static int MAX = Integer.MIN_VALUE;

    static void go(int a, int b, int sum){
        if(a==N || b==M){
            MAX = Math.max(MAX, sum);
            return;
        }
//
//        // a, b부터 끝까지 고려했을 때 최댓값. 이미 구해져 있으면 반환
//        if(dp[a][b] != 0) return dp[a][b];

        for(int i=a+1; i<N; i++) {
            for (int j=b+1; j < M; j++) {
                go(i, j, sum+W[A[i]-1][B[j]-1]);
            }
        }
    }

}
