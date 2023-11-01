package code;

import java.util.*;
import java.io.*;


public class Main_SFT_순서대로_방문하기
{
    static int N, M;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] point;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][N];
        visited = new boolean[N][N];
        point = new int[M][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = "0".compareTo(st.nextToken()) == 0;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken()) - 1;
            point[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        bfs();

        System.out.println(CNT);
    }

    static int CNT = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void bfs() {
        // r, c, next, visited
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {point[0][0], point[0][1], 1, 1<<(N*point[0][0]+point[0][1])});

        int nr, nc, next, visited;
        while(!queue.isEmpty()) {
            int[] temp = queue.poll();

            next = temp[2];
            visited = temp[3];

            if(next > M) continue;
            if(next == M && temp[0] == point[M-1][0] && temp[1] == point[M-1][1]) {
                CNT++;
                continue;
            }

            for(int d=0; d<4; d++) {
                nr = temp[0] + dr[d];
                nc = temp[1] + dc[d];

                if(!canGo(nr, nc) || (visited & (1<<(N*nr+nc))) != 0 ) continue;

                if(nr == point[next][0] && nc == point[next][1]) {
                    queue.add(new int[]{nr, nc, next+1, visited|(1<<(N*nr+nc))});
                } else {
                    queue.add(new int[]{nr, nc, next, visited|(1<<(N*nr+nc))});
                }
            }
        }
    }

    public static boolean canGo(int r, int c) {
        if(r<0 || c<0 || N<=r || N<=c) return false;
        if(!map[r][c]) return false; // true면 갈 수 있음
        return true;
    }
}
