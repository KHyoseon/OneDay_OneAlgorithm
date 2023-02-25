import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer = 0;
    static boolean[][] map, cleaned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        cleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = st.nextToken().equals("0");
            }
        }

        clean(R, C, D);
        System.out.println(answer);
    }

    // 북동남서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void clean(int r, int c, int d) {
        if(!cleaned[r][c]) {
            cleaned[r][c] = true;
            answer++;
        }

        int nr, nc, nd;
        for(int m=1; m<=4; m++) {
            nd = (4+(d-m))%4;
            nr = r + dr[nd];
            nc = c + dc[nd];

            if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
            if(map[nr][nc] && !cleaned[nr][nc]){
                clean(nr, nc, nd);
                return;
            }
        }

        nr = r + dr[(d+2)%4];
        nc = c + dc[(d+2)%4];
        if(map[nr][nc]) clean(nr, nc, d);
    }
}