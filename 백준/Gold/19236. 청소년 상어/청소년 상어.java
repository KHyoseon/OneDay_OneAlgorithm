import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Fish shark;
    static boolean[] eaten = new boolean[17];

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Fish[] fish = new Fish[17];  // 물고기 상태 저장
        int[][] map = new int[4][4]; // 물고기 배치 저장. 0==비어있음, -1==상어

        StringTokenizer st;
        int n, d;
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                n = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken()) - 1;
                fish[n] = new Fish(i, j, d);
                map[i][j] = n;
            }
        }

        int init = map[0][0];
        shark = new Fish(0, 0, fish[init].d);
        eaten[init] = true;
        map[0][0] = -1;

        backTracking(fish, map, init);

        System.out.println(MAX);
    }

    static int MAX = 0;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static void backTracking(Fish[] fish, int[][] map, int sum) throws CloneNotSupportedException {
        // move fish
        Fish[] copyFish = new Fish[17];
        int[][] copyMap = new int[4][4];
        for(int i=1; i<17; i++)
            copyFish[i] = fish[i].clone();
        for(int i=0; i<4; i++)
            copyMap[i] = map[i].clone();
        moveFish(copyFish, copyMap);

        // move shark
        int x = shark.x, y = shark.y, d = shark.d;
        int dist, nx, ny;

        // 상어의 방향으로 최대 3칸까지 이동
        for(dist=1; dist<4; dist++) {
            nx = x + dist*dx[d];
            ny = y + dist*dy[d];

            if(nx<0 || 4<=nx || ny<0 || 4<=ny) continue;
            if(copyMap[nx][ny] == 0) continue;

            // 이동할 칸에 물고기가 있음 -> 거기 있던 물고기 정보 백업
            int cf = copyMap[nx][ny];
            int cd = copyFish[cf].d;
            eaten[cf] = true;

            // 상어 이동
            copyMap[nx][ny] = -1;
            copyMap[x][y] = 0;
            shark.update(nx, ny, cd);

            backTracking(copyFish, copyMap, sum + cf);

            // 원상 복구
            copyMap[nx][ny] = cf;
            copyMap[x][y] = -1;
            shark.update(x, y, d);
            eaten[cf] = false;
        }

        MAX = Math.max(MAX, sum);
    }

    private static void moveFish(Fish[] fish, int[][] map) {
        int x, y, d;
        int nx=-1, ny=-1, nd=0;

        // 0~15번 물고기
        for(int i=1; i<=16; i++) {
            // 먹혔으면 건너 뜀
            if(eaten[i]) continue;

            x = fish[i].x;
            y = fish[i].y;
            d = fish[i].d;

            // 8방 탐색
            for(int m=0; m<8; m++) {
                nd = (d+m)%8;
                nx = x + dx[nd];
                ny = y + dy[nd];

                if(nx<0 || 4<=nx || ny<0 || 4<=ny) continue;
                if(map[nx][ny] == -1) continue;
                break;
            }

            // 이동할 수 없으면 건너뜀
            if(nx<0 || 4<=nx || ny<0 || 4<=ny || map[nx][ny] == -1) continue;

            fish[i].d = nd;

            // 이동할 칸에 이미 물고기가 있음 -> 거기 있던 물고기를 내 자리로 먼저 옮김
            if(map[nx][ny] >= 1) {
                int t = map[nx][ny];
                map[x][y] = t;
                fish[t].update(x, y, fish[t].d);
            } else {
                map[x][y] = 0;
            }
            // 빈칸으로 이동
            map[nx][ny] = i;
            fish[i].update(nx, ny, fish[i].d);
        }
    }

    static class Fish implements Cloneable {
        int x, y;
        int d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public void update(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        protected Fish clone() throws CloneNotSupportedException {
            return (Fish) super.clone();
        }
    }
}