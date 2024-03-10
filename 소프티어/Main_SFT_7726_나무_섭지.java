package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
남우: 상하좌우 이동 + 벽/격자 밖은 안됨
유령: 상하좌우 이동 + 격자 밖은 안됨

남우가 탈출구에 도착하는 시간 구하고 / 도착할 수 없다면 바로 No
나머지 유령들을 하나씩 구해서
남우보다 빨리 도착하는 유령이 있다면 No, 없다면 Yes 출력

맵에 D: 출구, N: 남우, G: 유령, #: 벽, .: 비어있음

 */

public class Main_SFT_7726_나무_섭지 {
    public static int N, M;
    public static char[][] map;
    public static int[] nam, exit;
    public static int[][] nVst, gVst;
    public static ArrayList<int[]> ghosts = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        nVst = new int[N][M];
        gVst = new int[N][M];

        // 맵 정보 받아옴
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();

            if(line.contains("D"))
                exit = new int[]{i, line.indexOf('D')};
            if(line.contains("N")) {
                nam = new int[]{i, line.indexOf('N')};
                nVst[nam[0]][nam[1]] = 1;
            }
            if(line.contains("G")) {
                ghosts.add(new int[]{i, line.indexOf('G')});
                gVst[i][line.indexOf('G')] = 1;
            }
        }

        // 유령 이동거리 계산
        ghostMove();

        // 남우 탈출
        if(!namMove()){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    private static void ghostMove() {
        Queue<int[]> queue = new LinkedList<>();
        for(int[] ghost: ghosts) {
            queue.add(ghost);
        }
        bfs(queue, true, gVst);
    }

    private static boolean namMove() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(nam);
        int ret = bfs(queue, false, nVst);
        if(ret == Integer.MAX_VALUE) return false;
        return true;
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static int bfs(Queue<int[]> queue, boolean isGhost, int[][] visited) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                // 갈 수 있는지
                if(!canMove(nr, nc, isGhost, visited[r][c], visited)) continue;
                // 지금보다 1초 더 걸림
                visited[nr][nc] = visited[r][c] + 1;
                queue.add(new int[]{nr, nc});
                // 남우) 출구라면 종료
                if(!isGhost && nr==exit[0] && nc==exit[1]) return visited[nr][nc];
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean canMove(int nr, int nc, boolean isGhost, int prev, int[][] visited) {
        if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
        if(visited[nr][nc] != 0) return false;
        if(isGhost) return true;
        // 아래는 남우만 해당. 1) 벽인가? 2) 유령보다 빨리 도달할 수 없는 곳인가?
        if(map[nr][nc] == '#') return false;
        if(gVst[nr][nc] <= prev + 1) return false;
        return true;
    }
}