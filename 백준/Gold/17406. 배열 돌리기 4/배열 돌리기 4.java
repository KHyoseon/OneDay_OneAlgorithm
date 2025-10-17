import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Move[] moves;
    static int N, M, K;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        moves = new Move[K];
        int[] rows = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                rows[i] += map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            moves[i] = new Move(r-1, c-1, s);
        }

        order(0, K, map, rows, 0);

        System.out.println(MIN);
    }

    private static void order(int cur, int K, int[][] map, int[] rows, int flag) {
        if(cur==K) {
            for(int row: rows) {
                if(row < MIN)
                    MIN = row;
            }
            return;
        }

        int[][] copyMap = new int[N][M];
        int[] copyRows;

        for(int i=0; i<K; i++) {
            if((flag & 1<<i) != 0) continue;

            for(int r=0; r<N; r++)
                copyMap[r] = map[r].clone();
            copyRows = rows.clone();
            turn(moves[i], copyMap, copyRows);
            order(cur + 1, K, copyMap, copyRows, flag | 1<<i);
        }
    }

    private static void turn(Move move, int[][] map, int[] rows) {
        int r = move.r;
        int c = move.c;
        int s = move.s;

        for(int p=1; p<=s; p++) {
            int ul = map[r-p][c-p];
            int dr = map[r+p][c+p];

            rows[r-p] -= map[r-p][c+p];
            rows[r+p] -= map[r+p][c-p];
            rows[r-p] += map[r-p][c-p];
            rows[r+p] += map[r+p][c+p];

            // 왼쪽 오른쪽
            for(int i=0; i<2*p; i++) {
                rows[r-p+i] -= map[r-p + i][c-p];
                map[r-p+i][c-p] = map[r-p + i + 1][c-p];
                rows[r-p+i] += map[r-p + i][c-p];

                rows[r+p-i] -= map[r+p - i][c+p];
                map[r+p - i][c+p] = map[r+p - i - 1][c+p];
                rows[r+p-i] += map[r+p - i][c+p];
            }

            // 위쪽 아래쪽
            for(int j=0; j<2*p; j++) {
                map[r-p][c+p - j] = map[r-p][c+p - j - 1];
                map[r+p][c-p + j] = map[r+p][c-p + j + 1];
            }
            map[r-p][c-p+1] = ul;
            map[r+p][c+p-1] = dr;
        }
    }
}

class Move {
    int r, c, s;
    public Move(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}
