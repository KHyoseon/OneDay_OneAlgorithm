import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, MAX = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<ArrayList<int[][]>> tetromino = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            tetromino.add(new ArrayList<>());
        }

        tetromino.get(0).add(new int[][] {{0, 1}, {0, 2}, {0, 3}});
        tetromino.get(0).add(new int[][] {{1, 0}, {2, 0}, {3, 0}});

        tetromino.get(1).add(new int[][] {{0, 1}, {1, 0}, {1, 1}});

        tetromino.get(2).add(new int[][] {{1, 0}, {2, 0}, {2, 1}});
        tetromino.get(2).add(new int[][] {{0, 1}, {0, 2}, {1, 0}});
        tetromino.get(2).add(new int[][] {{0, 1}, {1, 1}, {2, 1}});
        tetromino.get(2).add(new int[][] {{1, -2}, {1, -1}, {1, 0}});

        tetromino.get(2).add(new int[][] {{1, 0}, {2, 0}, {2, -1}});
        tetromino.get(2).add(new int[][] {{1, 0}, {1, 1}, {1, 2}});
        tetromino.get(2).add(new int[][] {{0, 1}, {1, 0}, {2, 0}});
        tetromino.get(2).add(new int[][] {{0, 1}, {0, 2}, {1, 2}});

        tetromino.get(3).add(new int[][] {{1, 0}, {1, 1}, {2, 1}});
        tetromino.get(3).add(new int[][] {{0, 1}, {1, -1}, {1, 0}});
        tetromino.get(3).add(new int[][] {{1, -1}, {1, 0}, {2, -1}});
        tetromino.get(3).add(new int[][] {{0, 1}, {1, 1}, {1, 2}});

        tetromino.get(4).add(new int[][] {{0, 1}, {0, 2}, {1, 1}});
        tetromino.get(4).add(new int[][] {{1, -1}, {1, 0}, {2, 0}});
        tetromino.get(4).add(new int[][] {{1, -1}, {1, 0}, {1, 1}});
        tetromino.get(4).add(new int[][] {{1, 0}, {1, 1}, {2, 0}});


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (ArrayList<int[][]> blocks : tetromino) {
                    for (int[][] block : blocks) {
                        int sum = func(block, i, j);
                        if (sum != -1){
                            MAX = Math.max(MAX, sum);
                        }
                    }
                }
            }
        }

        System.out.println(MAX);
    }

    private static int func(int[][] block, int i, int j) {
        int sum = map[i][j];
        int nr, nc;
        for(int[] pair: block) {
            nr = i + pair[0];
            nc = j + pair[1];
            if(nr < 0 || nc < 0 || N <= nr || M <= nc) return -1;
            sum += map[nr][nc];
        }
        return sum;
    }
}