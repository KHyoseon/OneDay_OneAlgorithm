import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N && st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        dfs();

        int MAX = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                MAX = Math.max(MAX, dp[r][c]);
            }
        }
        System.out.println(MAX);
    }

    private static void dfs() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(dp[i][j] != -1) continue;
                if(i==2 && j==3)
                    dfs(i, j);
                else dfs(i, j);
            }
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    private static int dfs(int r, int c) {
        int ret = -1;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!canGo(r, c, nr, nc)) continue;

            if(dp[nr][nc] != -1) {
                ret = dp[nr][nc];
            } else {
                ret = dfs(nr, nc);
            }
            dp[r][c] = Math.max(ret + 1, dp[r][c]);
        }

        return dp[r][c] = (ret==-1? 1: dp[r][c]);
    }

    private static boolean canGo(int r, int c, int nr, int nc) {
        if(nr<0 || N<=nr || nc<0 || N<=nc) return false;
        if(map[r][c] >= map[nr][nc]) return false;
        return true;
    }

}