import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1189_컴백홈 {
    static int R, C, K, answer;
    static boolean[][] map;

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
                if (input.charAt(c) == '.') {
                    map[r][c] = true;
                }
            }
        }

        answer = 0;
        // bfs
        bfs(R-1, 0);

        System.out.println(answer);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int i, int j) {
        Queue<BFS> queue = new LinkedList<>();
        boolean[][] init = new boolean[R][C];
        init[i][j] = true;
        queue.add(new BFS(i, j, init.clone()));

        int nr, nc;
        int cnt = 1;

        BFS cur;
        boolean[][] copy;
        while(!queue.isEmpty()) {
            Queue<BFS> queue2 = new LinkedList<>();
            while(!queue.isEmpty()){
                cur = queue.poll();

                for (int d = 0; d < 4; d++) {
                    nr = cur.r + dr[d];
                    nc = cur.c + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (cur.visited[nr][nc] && !map[nr][nc]) continue;

                    copy = cur.visited.clone();
                    copy[nr][nc] = true;
                    queue2.add(new BFS(nr, nc, copy));
                }
            }
            cnt++;

            if(cnt != K) {
                queue.addAll(queue2);
                continue;
            }

            for(BFS node: queue2){
                if(node.r==0 && node.c==C-1)
                    answer++;
            }

            return;
        }
    }

    static class BFS{
        int r, c;
        boolean[][] visited;

        public BFS(int r, int c, boolean[][] v) {
            this.r = r;
            this.c = c;
            this.visited = v;
        }
    }
}
