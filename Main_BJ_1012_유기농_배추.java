import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1012_유기농_배추 {
    static int N, M, K; // R, C
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]); // c
            N = Integer.parseInt(input[1]); // r
            K = Integer.parseInt(input[2]);

            map = new int[N][M];
            visited = new boolean[N][M];

            for(int i=0; i<K; i++) {
                input = br.readLine().split(" ");
                map[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = 1;
            }

            System.out.println(bfs());
        }
    }

    static int[][] direct = {
            {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    private static int bfs() {
        int cnt = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c]==1 && !visited[r][c]) {
                    visited[r][c] = true;
                    bfs(r, c);
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});

        int[] coordinate;
        int nr, nc;
        while(!queue.isEmpty()) {
            coordinate = queue.poll();
            for (int d = 0; d < 4; d++) {
                nr = coordinate[0] + direct[d][0];
                nc = coordinate[1] + direct[d][1];

                if (0 > nr || nr >= N || 0 > nc || nc >= M
                        || map[nr][nc] == 0 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
    }
}
