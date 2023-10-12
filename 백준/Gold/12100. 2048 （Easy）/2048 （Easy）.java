import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        skip = new boolean[N];
        play(map, 0);
        System.out.println(MAX);
    }

    static boolean[] skip;

    private static void play(int map[][], int cnt) {
        if(cnt == 5) {
            MAX = Math.max(MAX, getMaxValue(map));
            return;
        }

        for(int d=0; d<4; d++) {
            int[][] moved = new int[N][N];
            for(int i=0; i<N; i++) {
                moved[i] = map[i].clone();
            }
            move(d, moved);
            play(moved, cnt + 1);
        }
    }

    private static int getMaxValue(int[][] map) {
        int max = 0;
        for(int[] line: map) {
            for(int num: line)
                max = Math.max(max, num);
        }
        return max;
    }

    private static void move(int d, int[][] clone) {
        switch (d) {
            case 0:
                moveUp(clone);
                break;
            case 1:
                moveRight(clone);
                break;
            case 2:
                moveDown(clone);
                break;
            case 3:
                moveLeft(clone);
                break;
        }
    }

    private static void moveLeft(int[][] map) {
        for(int r=0; r<N; r++) {
            int blank = 0, tmp;
            while(blank < N-1) {
                // 0이면 당기고 0이 아니면 합칠 거 찾음
                if(map[r][blank] == 0) {
                    for (tmp = blank + 1; tmp < N && map[r][tmp]==0; tmp++) ;
                    if (tmp < N) {
                        map[r][blank] = map[r][tmp];
                        map[r][tmp] = 0;
                    }
                }
                for (tmp = blank + 1; tmp < N && map[r][tmp]==0; tmp++) ;
                if(tmp < N && map[r][blank]==map[r][tmp]) {
                    map[r][blank] *= 2;
                    map[r][tmp] = 0;
                }
                blank++;
            }
        }
    }

    private static void moveRight(int[][] map) {
        for(int r=0; r<N; r++) {
            int blank = N-1, tmp;
            while(blank > 0) {
                if(map[r][blank] == 0) {
                    for (tmp = blank - 1; tmp >= 0 && map[r][tmp] == 0; tmp--) ;
                    if (tmp >= 0) {
                        map[r][blank] = map[r][tmp];
                        map[r][tmp] = 0;
                    }
                }
                for(tmp = blank-1; tmp>=0 && 0==map[r][tmp]; tmp--);
                if(tmp >= 0 && map[r][blank]==map[r][tmp]) {
                    map[r][blank] *= 2;
                    map[r][tmp] = 0;
                }
                blank--;
            }
        }
    }

    private static void moveUp(int[][] map) {
        for(int c=0; c<N; c++) {
            int blank = 0, tmp;
            while(blank < N-1) {
                if(map[blank][c] == 0) {
                    for (tmp = blank+1; tmp < N && map[tmp][c] == 0; tmp++) ;
                    if(tmp < N) {
                        map[blank][c] = map[tmp][c];
                        map[tmp][c] = 0;
                    }
                }
                for (tmp = blank+1; tmp < N && map[tmp][c] == 0; tmp++) ;
                if (tmp < N && map[blank][c] == map[tmp][c]) {
                    map[blank][c] *= 2;
                    map[tmp][c] = 0;
                }
                blank++;
            }
        }
    }
    
    private static void moveDown(int[][] map) {
        for(int c=0; c<N; c++) {
            int blank = N-1, tmp;
            while(blank > 0) {
                if(map[blank][c] == 0) {
                    for (tmp = blank-1; tmp >= 0 && map[tmp][c] == 0; tmp--) ;
                    if(tmp >= 0) {
                        map[blank][c] = map[tmp][c];
                        map[tmp][c] = 0;
                    }
                }
                for (tmp = blank-1; tmp >= 0 && map[tmp][c] == 0; tmp--) ;
                if (tmp >= 0 && map[blank][c] == map[tmp][c]) {
                    map[blank][c] *= 2;
                    map[tmp][c] = 0;
                }
                blank--;
            }
        }
    }
}