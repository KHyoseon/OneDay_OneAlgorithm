import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[] AC = {-1, -1};
    static int[][] map, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        temp = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1){
                    if(AC[0]==-1)   AC[0] = i;
                    else            AC[1] = i;
                }
            }
        }

        simulate();

        int answer = 0;
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] > 0) answer += map[r][c];
            }
        }

        System.out.println(answer);
    }

    private static void simulate() {
        while (T > 0) {
            // 미세먼지 확산
            hwaksan();
            // 공기청정기 작동
            cleaner();
            T--;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static void hwaksan() {
        // 먼지는 네 방향으로 확산. 빈 공간이 아니면 일어나지 않음. A/5만큼 확산됨
        int spread, nr, nc;

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] < 5){
                    temp[r][c] += map[r][c];
                    continue;
                }
                spread = 0;
                // 사방으로 번짐
                for(int d=0; d<4; d++) {
                    nr = r+dr[d];
                    nc = c+dc[d];

                    if (nr<0 || R<=nr || nc<0 || C<=nc || map[nr][nc]==-1) continue;
                    temp[nr][nc] += (map[r][c] / 5);
                    spread += (map[r][c] / 5);
                }
                // 남아 있는 먼지
                temp[r][c] += (map[r][c] - spread);
            }
        }

        // 원래 맵에 덮어 씌우고 temp 초기화
        for(int i=0; i<R; i++) {
            map[i] = Arrays.copyOf(temp[i], C);
            Arrays.fill(temp[i], 0);
        }
    }

    private static void cleaner() {
        // 공기청정기 위쪽은 반시계로, 아래는 시계방향으로 순환한다.
        // 미세먼지는 바람 방향대로 이동. 공기청정기로 들어가면 사라짐.

        /* 위쪽 */
        int LUCorner = map[0][0];
        int RDCorner = map[AC[0]][C-1];
        // 맨 윗줄, 아랫줄
        for(int c=0; c<C-1; c++) {
            if(map[0][c] != -1) map[0][c] = Math.max(map[0][c + 1], 0);
            if(map[AC[0]][C-1-c] != -1) map[AC[0]][C-1-c] = Math.max(map[AC[0]][C-2-c], 0);
        }
        // 가운데
        for(int r=1; r<AC[0]; r++) {
            if(map[AC[0]-r][0] != -1) map[AC[0]-r][0] = Math.max(map[AC[0]-r - 1][0], 0);
            if(map[r - 1][C - 1] != -1) map[r - 1][C - 1] = Math.max(map[r][C - 1], 0);
        }
        map[1][0] = LUCorner;
        map[AC[0] - 1][C - 1] = RDCorner;

        /* 아랫쪽 */
        int LDCorner = map[R-1][0];
        int RUCorner = map[AC[1]][C-1];
        // 맨 윗줄, 아랫줄
        for(int c=0; c<C-1; c++) {
            if(map[AC[1]][c+1] != -1) map[AC[1]][C-1-c] = Math.max(map[AC[1]][C-2-c], 0);
            if(map[R-1][c] != -1) map[R-1][c] = Math.max(map[R-1][c + 1], 0);
        }
        // 가운데
        for(int r=AC[1]; r<R-1; r++) {
            if(map[r][0] != -1) map[r][0] = Math.max(map[r+1][0], 0);
            if(map[r][C - 1] != -1) map[R-1+AC[1]-r][C - 1] = Math.max(map[R-2+AC[1]-r][C - 1], 0);
        }
        map[R-2][0] = LDCorner;
        map[AC[1]+1][C - 1] = RUCorner;
    }

}