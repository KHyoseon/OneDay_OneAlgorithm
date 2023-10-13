import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,  M, MAX = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(MAX);
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int curR, int curC, int sum, int depth) {
        if(depth == 4) {
            MAX = Math.max(MAX, sum);
            return;
        }

        int nr, nc;
        for(int d = 0; d < 4; d++) {
            nr = curR + dr[d];
            nc = curC + dc[d];

            if(nr < 0 || nc < 0 || N <= nr || M <= nc || visited[nr][nc]) continue;

            // 凸 블록
            if(depth == 2) {
                // 이미 (nr, nc) 방문했다 치고
                visited[nr][nc] = true;
                // 현재 위치(curR, curC)에서 다른 (nr, nc)을 찾음
                dfs(curR, curC, sum + map[nr][nc], depth + 1);
                visited[nr][nc] = false;
            }

            visited[nr][nc] = true;
            dfs(nr, nc, sum + map[nr][nc], depth + 1);
            visited[nr][nc] = false;
            
        }
    }
}