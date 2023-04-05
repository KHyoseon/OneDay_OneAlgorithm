import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int limit = (Math.min(N, M))/2;

        ArrayList<Integer> round = new ArrayList<>();

        for (int w = 0; w < limit; w++) {
            rotateCircle(w, R);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void rotateCircle(int w, int R) {
        for (int r = 0, l = R % ((N + M - 2 - 4 * w) * 2); r < l; r++) {
            rotate(w);
        }
    }

    private static void rotate(int w) {
        int LUCorner = map[w][w];
        int RDCorner = map[N-1-w][M-1-w];
        // 가로
        for(int m=w; m<M-1-w; m++) {
            map[w][m] = map[w][m + 1];
            map[N-1-w][M-1-m] = map[N-1-w][M-2-m];
        }
        // 세로
        for(int r=w; r<N-1-w; r++) {
            map[N-1-r][w] = map[N-2-r][w];
            map[r][M-1-w] = map[r+1][M-1-w];
        }
        map[w+1][w] = LUCorner;
        map[N-2-w][M-1-w] = RDCorner;
    }
}