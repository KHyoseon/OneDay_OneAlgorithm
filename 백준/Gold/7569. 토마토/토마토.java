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

        ArrayList<int[]> ripen = new ArrayList<>();

        // 그래프 구성
        for(int k=0; k<H; k++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp == 1){
                        ripen.add(new int[]{k, i, j});
                        day[k][i][j] = 0;
                    } else if (tmp == 0) {
                        unRipenCnt++;
                        day[k][i][j] = Integer.MAX_VALUE;
                    } else {
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


    private static void bfs(ArrayList<int[]> ripped){
        for(int[] rip: ripped) {
            bfs(rip[0], rip[1], rip[2]);
        }
    }

    // 위 아래 왼 오 앞 뒤
    static int[] dr = {0, 0, 0, 0, 1, -1};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {1, -1, 0, 0, 0, 0};
    private static void bfs(int k, int i, int j) {
        // i, j, k, time
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{k, i, j, 0});

        int[] cur = {};
        while(!queue.isEmpty()) {
            cur = queue.poll();

            for(int d=0; d<6; d++) {
                int nh = cur[0] + dh[d];
                int nr = cur[1] + dr[d];
                int nc = cur[2] + dc[d];
                int nd = cur[3] + 1;

                // 범위 내 && 토마토 칸이 맞는지
                if(!canMove(nh, nr, nc)) continue;
                // day에 더 빨리 갈 방법이 이미 있는지
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