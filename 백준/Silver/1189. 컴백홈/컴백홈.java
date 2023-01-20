import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, K, answer;
    static boolean[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];

        // 맵 구성
        for(int r=0; r<R; r++){
            String input = br.readLine();
            for(int c=0; c<C; c++) {
                if (input.charAt(c) == '.') map[r][c] = true;
            }
        }

        answer = 0;
        visited = new boolean[R][C];
        visited[R-1][0] = true;

        dfs(R-1, 0, 1);

        System.out.println(answer);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void dfs(int r, int c, int k){
        if(r==0 && c==C-1 && k==K){
            ++answer;
            return;
        }

        int nr, nc;
        for(int d=0; d<4; d++){
            nr = r+ dr[d];
            nc = c+ dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (visited[nr][nc] || !map[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, k+1);
            visited[nr][nc] = false;
        }
    }
}