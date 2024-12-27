import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int bfs(){
        boolean splited = false, allMelted;
        int year = 0;

        while(true) {
            visited = new boolean[N][M];
            allMelted = true;

            for (int r = 1; r < N-1; r++) {
                for (int c = 1; c < M-1; c++) {
                    if (visited[r][c] || map[r][c] == 0) continue;
                    if(splited) return year;

                    visited[r][c] = true;
                    splited = true;
                    allMelted = false;
                    bfs(r, c);
                }
            }

            if(allMelted) break;

            splited = false;
            ++year;
        }

        return 0;
    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> meltQ = new LinkedList<>();
        queue.add(new int[]{r, c});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int melt = 0;

            for(int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(nr<0 || nc<0 || N<=nr || M<=nc) continue;
                if(map[nr][nc]==0){
                    melt++;
                    continue;
                }
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }

            meltQ.add(new int[]{cur[0], cur[1], melt});
        }

        while(!meltQ.isEmpty()) {
            int[] cur = meltQ.poll();
            map[cur[0]][cur[1]] = Math.max(0, map[cur[0]][cur[1]]-cur[2]);
        }
    }
}