import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Queue;

public class Main {
    static int N;
    static boolean[][] map, visited;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = line.charAt(j)=='1';
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(visited[r][c] || !map[r][c]) continue;
                visited[r][c] = true;
                bfs(r, c);
            }
        }
        Collections.sort(list);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(list.size()+"\n");
        for(long size: list)
            bw.write(size+"\n");
        bw.flush();
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});

        long size = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            ++size;

            for(int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(nr<0 || nc<0 || N<=nr || N<=nc) continue;
                if(!map[nr][nc] || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }

        list.add(size);
    }
}