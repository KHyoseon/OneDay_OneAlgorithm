import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB {
    static int ROW, COLUMN, answer;
    static boolean[][] visited;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COLUMN = Integer.parseInt(st.nextToken());

        map = new char[ROW][COLUMN];
        visited = new boolean[ROW][COLUMN];

        for(int r=0; r<ROW; r++){
            map[r] = br.readLine().replace(" ", "").toCharArray();
        }

        answer = 0;
        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        for(int r = 0; r< ROW; r++)
            for(int c = 0; c< COLUMN; c++){
                if(map[r][c]=='1' || visited[r][c]) continue;
                visited[r][c] = true;
                bfs(r, c);
                answer++;
            }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int cr, int cc){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{cr, cc});
        visited[cr][cc] = true;

        int nr, nc;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r=cur[0], c=cur[1];
            for(int d=0; d<4; d++){
                nr = (r+dr[d] + ROW)%ROW;
                nc = (c+dc[d] + COLUMN)%COLUMN;

                if(map[nr][nc] == '1' || visited[nr][nc]) continue;
                visited[nr][nc] = true;

                queue.add(new int[]{nr, nc});
            }
        }
    }
}
