import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프_옮기기_1 {

    static int N;
    static int[][][] memo;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // 이전 방향(가로, 대각, 세로), R, C
        memo = new int[3][N][N];
        map = new boolean[N][N];

        for(int d=0; d<3; d++)
            for(int i=0; i<N; i++)
               Arrays.fill(memo[d][i], -1);

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = st.nextToken().equals("1");
        }

        map[0][0] = map[0][1] = true;

        dfs(0, 0, 1);

        System.out.print(memo[0][0][1]);
    }

    private static int dfs(int prev, int r, int c) {
        if(memo[prev][r][c] != -1)  return memo[prev][r][c];

        if (r==N-1 && c==N-1)       return 1;

        memo[prev][r][c] = 0;

        for(int d=0; d<3; d++){
            if (prev==0 && d==2) continue;
            else if (prev==2 && d==0) continue;

            switch (d){
                case 0:
                    // 가로
                    if(c >= N-1) continue;
                    if(map[r][c+1]) continue;
                    map[r][c+1] = true;
                    memo[prev][r][c] += dfs(d, r, c+1);
                    map[r][c+1] = false;
                    break;
                case 1:
                    // 대각선
                    if(r >= N-1 || c >= N-1) continue;
                    if(map[r+1][c] || map[r][c+1] || map[r+1][c+1]) continue;
                    map[r+1][c] = map[r][c+1] = map[r+1][c+1] = true;
                    memo[prev][r][c] += dfs(d, r+1, c+1);
                    map[r+1][c] = map[r][c+1] = map[r+1][c+1] = false;
                    break;
                case 2:
                    // 세로
                    if(r >= N-1) continue;
                    if(map[r+1][c]) continue;
                    map[r+1][c] = true;
                    memo[prev][r][c] += dfs(d, r+1, c);
                    map[r+1][c] = false;
                    break;
            }
        }

        return memo[prev][r][c];
    }
}
