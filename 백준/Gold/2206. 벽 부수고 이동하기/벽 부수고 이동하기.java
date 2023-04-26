import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M;
    static boolean[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new boolean[N][M];
        dp = new int[N][M][2];

        char[] line;
        for(int r=0; r<N; r++) {
            line = br.readLine().toCharArray();
            for(int c=0; c<M; c++) {
                map[r][c] = ('0' == line[c]);
            }
        }
        
        if(N==1 && M==1) {
            System.out.println(1);
            return;
        }

        dp[0][0][0] = 1;
        dp[N - 1][M - 1][0] = dp[N - 1][M - 1][1] = Integer.MAX_VALUE;
        bfs();

        int min = Math.min(dp[N-1][M-1][0], dp[N-1][M-1][1]);
        System.out.println(min == Integer.MAX_VALUE? -1: min);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});

        int[] cur;
        int r, c, broke, nr, nc;

        while (!queue.isEmpty()) {
            cur = queue.poll();
            r = cur[0];
            c = cur[1];

            if (r == N - 1 && c == M - 1) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                nr = r + dr[d];
                nc = c + dc[d];
                broke = cur[2];

                // 범위 밖
                if (nr < 0 || nc < 0 || N <= nr || M <= nc) continue;
                // 이미 한번 부쉈는데 또 벽을 만남
                if (broke == 1 && !map[nr][nc]) continue;

                // 벽이면 부숨
                if (!map[nr][nc]) broke = 1;

                // 방문한 적 없거나 이 루트가 제일 빠르면 dp 갱신
                if (dp[nr][nc][broke] == 0 || dp[nr][nc][broke] > dp[r][c][cur[2]] + 1) {
                    dp[nr][nc][broke] = dp[r][c][cur[2]] + 1;
                    queue.add(new int[]{nr, nc, broke});
                }
            }
        }
    }
}