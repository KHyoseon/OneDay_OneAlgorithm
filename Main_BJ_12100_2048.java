package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_12100_2048 {
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
            int[][] moved = map.clone();
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
            int last, empty = 0, target = 1;

            for(int c=0; c<N; c++) {
                for (; target < N && map[r][target] == 0; target++) {
                    if(map[r][c] == map[r][target]) {
                        map[r][c] *= 2;
                        map[r][target] = 0;
                    } else {
                        break;
                    }
                }

            }

            while (true) {
                for (; empty < N && map[r][empty] != 0; empty++) ;
                last = empty==0? 0: empty-1;
                for (; target < N && map[r][target] == 0; target++) ;
                if (empty >= N || target >= N) break;
                if(map[r][last]==map[r][target]) {
                    map[r][last] *= 2;
                    map[r][target] = 0;
                } else {
                    map[r][empty] = map[r][target];
                    map[r][target] = 0;
                }
            }
        }
    }

    private static void moveRight(int[][] map) {
        for(int r=N-1; r>=0; r--) {
            int last, empty = N-1, target = N-2;
            while (true) {
                for (; empty >= 0 && map[r][empty] != 0; empty--) ;
                last = empty==N-1? empty: empty+1;
                for (; target >= 0 && map[r][target] == 0; target--) ;
                if (empty < 0 || target < 0) break;
                if(map[r][last]==map[r][target]) {
                    map[r][last] *= 2;
                    map[r][target] = 0;
                } else {
                    map[r][empty] = map[r][target];
                    map[r][target] = 0;
                }
            }
        }
    }

    private static void moveUp(int[][] map) {
        for(int c=0; c<N; c++) {
            int last, empty = 0, target = 1;
            while (true) {
                for (; empty < N && map[empty][c] != 0; empty++) ;
                last = empty==0? 0: empty-1;
                for (; target < N && map[target][c] == 0; target++) ;
                if (empty >= N || target >= N) break;
                if(map[last][c]==map[target][c]) {
                    map[last][c] += map[target][c];
                    map[target][c] = 0;
                } else {
                    map[empty][c] = map[target][c];
                    map[target][c] = 0;
                }
            }
        }
    }

    private static void moveDown(int[][] map) {
        for(int c=N-1; c>=0; c--) {
            int last, empty = N-1, target = N-2;
            while (true) {
                for (; empty >= 0 && map[empty][c] != 0; empty--) ;
                last = empty==N-1? empty: empty+1;
                for (; target >= 0 && map[target][c] == 0; target--) ;
                if (empty < 0 || target < 0) break;
                if(map[last][c]==map[target][c]) {
                    map[last][c] += map[target][c];
                    map[target][c] = 0;
                } else {
                    map[empty][c] = map[target][c];
                    map[target][c] = 0;
                }
            }
        }
    }
}
