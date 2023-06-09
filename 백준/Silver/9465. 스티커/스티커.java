import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] stickers, memo;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            stickers = new int[2][N];
            memo = new int[2][N];

            StringTokenizer st;
            for(int r=0; r<2; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    stickers[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            memo[0][N - 1] = stickers[0][N - 1];
            memo[1][N - 1] = stickers[1][N - 1];

            for(int c=N-1; c>0; c--) {
                memo[0][c-1] = Math.max(stickers[0][c-1] + memo[1][c], memo[0][c]);
                memo[1][c-1] = Math.max(stickers[1][c-1] + memo[0][c], memo[1][c]);
            }

            sb.append(Math.max(memo[0][0], memo[1][0])).append("\n");
        }

        System.out.print(sb);
    }

}