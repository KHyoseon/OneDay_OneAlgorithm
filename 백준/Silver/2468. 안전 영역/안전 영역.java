import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, MAX = 1;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        bfs(min, max);

        System.out.println(MAX);
    }

    private static void bfs(int min, int max){
        int sum = 0;
        for(int l=min; l<=max; l++) {
            boolean[][] visited = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visited[i][j] ||
                            map[i][j] <= l) continue;
                    visited[i][j] = true;
                    bfs(i, j, l, visited);
                    ++sum;
                }
            }
            MAX = Math.max(MAX, sum);
            sum = 0;
        }
    }

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {-1, 1, 0, 0};
    private static void bfs(int i, int j, int LIMIT, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        int[] cur;
        while(!queue.isEmpty()) {
            cur = queue.poll();

            for(int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(!canMove(nr, nc)
                    || visited[nr][nc]
                    || map[nr][nc] <= LIMIT) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
    }

    private static boolean canMove(int nr, int nc) {
        if(nr<0 || nc<0) return false;
        if(N<=nr || N<=nc) return false;
        return true;
    }
}