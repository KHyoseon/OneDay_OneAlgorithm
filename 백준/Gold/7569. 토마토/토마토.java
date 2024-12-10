import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, H, unRipenCnt = 0;
    private static int[][][] day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        day = new int[H][N][M];

        // i, j, k, time
        Queue<int[]> ripen = new LinkedList<>();

        for(int k=0; k<H; k++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<M; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp == 1){
                        // 익은 토마토면 따로 위치 빼두고 day에 0일 표기
                        ripen.add(new int[]{k, i, j, 0});
                        day[k][i][j] = 0;
                    } else if (tmp == 0) {
                        // 안익은 토마토면 따로 카운트하고 day에 무한일 표기
                        unRipenCnt++;
                        day[k][i][j] = Integer.MAX_VALUE;
                    } else {
                        // 빈칸이면 day에 불가능(-1) 표기
                        day[k][i][j] = -1;
                    }
                }
            }
        }

        // 다 익은 상태면 종료
        if(unRipenCnt == 0) {
            System.out.println(0);
            return;
        }

        bfs(ripen);

        // 안익은 토마토 있는지 확인
        if(unRipenCnt > 0) {
            System.out.println(-1);
            return;
        }

        int max = 0;
        for(int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    max = Math.max(day[k][i][j], max);
                }
            }
        }

        System.out.println(max);
    }

    // h위 h아래 r상 r하 c왼 c오
    static int[] dr = {0, 0, 0, 0, 1, -1};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {1, -1, 0, 0, 0, 0};

    private static void bfs(Queue<int[]> queue) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int d=0; d<6; d++) {
                int nh = cur[0] + dh[d];
                int nr = cur[1] + dr[d];
                int nc = cur[2] + dc[d];
                int nd = cur[3] + 1;

                // 범위 내 && 빈칸이 아닌지
                if(!canMove(nh, nr, nc)) continue;
                // 해당 칸의 토마토가 더 빨리 익는 경우가 이미 있는지
                if(day[nh][nr][nc] <= nd) continue;

                if(day[nh][nr][nc] == Integer.MAX_VALUE)    --unRipenCnt;
                day[nh][nr][nc] = nd;
                queue.add(new int[]{nh, nr, nc, nd});
            }
        }
    }

    private static boolean canMove(int nh, int nr, int nc) {
        if(nr<0 || nc<0 || nh<0) return false;
        if(N<=nr || M<=nc || H<=nh) return false;
        return day[nh][nr][nc]!=-1;
    }
}