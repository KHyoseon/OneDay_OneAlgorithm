import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1520_내리막_길 {
    static int N, M;
    static int[][] map, memo;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        memo = new int[N][M]; // -1: 아직 탐색 안함, 0~: 탈출 가능 가짓수

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(memo[i], -1);
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(memo[0][0]);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int dfs(int r, int c) {
        // 탈출 성공
        if(r==N-1 && c==M-1)    return 1;
        // 메모이제이션
        if(memo[r][c] != -1)    return memo[r][c];

        memo[r][c] = 0;

        // 사방탐색
        int nr, nc;
        for(int d=0; d<4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            if(nr<0 || nr>=N || nc<0 || nc>=M
                    || map[nr][nc] >= map[r][c])		continue;

            memo[r][c] += dfs(nr, nc);
        }

        return memo[r][c];
    }
}