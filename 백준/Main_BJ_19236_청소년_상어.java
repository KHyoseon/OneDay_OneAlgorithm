package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_19236_청소년_상어 {

    static Fish[] fish = new Fish[17];
    /*static int[][] num = new int[4][4];
    static int[][] dir = new int[4][4];*/
    static int[] shark;
    static boolean[] eaten = new boolean[17];
    static int MAX = 0;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int n, d;

        int[][] num = new int[4][4];
        int[][] dir = new int[4][4];

        for(int x=0; x<4; x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<4; y++) {
                num[x][y] = n = Integer.parseInt(st.nextToken());
                dir[x][y] = d = Integer.parseInt(st.nextToken());
                fish[n] = new Fish(x, y, n, d-1);
            }
        }

        shark = new int[]{0, 0, dir[0][0]};
        dfs(num, dir, num[0][0]);
    }

    private static void dfs(int[][] num, int[][] dir, int i) {
        // move fish
        moveFish();

        // find way
        int x = shark[0], y = shark[1], d = shark[2];
        int nx = x, ny = y;
        boolean[] jumpTo = new boolean[3];
        for(int dist=0; dist<3; dist++) {
            nx += dx[d];
            ny += dy[d];

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny) break;
            if((num[nx][ny] == -1)) continue;

            jumpTo[dist] = true;
        }

        // shark go

    }

    private static void moveFish() {
    }
}


class Main_BJ_19236_청소년_상어2 {
    static Fish[] fish16 = new Fish[17];
    static Shark shark = new Shark();
    static Fish temp;
    static Fish[][] map = new Fish[4][4];
    static boolean[] eaten = new boolean[17];
    static int MAX = 0;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int num, dir;

        for(int x=0; x<4; x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<4; y++) {
                num = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken());
                map[x][y] = new Fish(x, y, num, dir-1);
                fish16[num] = map[x][y];
            }
        }

        shark.x = 0;
        shark.y = 0;
        shark.haveAte += map[0][0].n;
        shark.d = map[0][0].d;
        eaten[map[0][0].n] = true;
        dfs(eaten);

        System.out.println(MAX);
    }

    private static void dfs(boolean[] eaten) {
        // move fish
        moveFish(map);

        // can move?
        int x= shark.x, y= shark.y, d= shark.d;

        int nx = x, ny = y;
        boolean[] jumpTo = new boolean[3];
        for(int dist=0; dist<3; dist++) {
            nx += dx[d];
            ny += dy[d];

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny) break;
            if((map[nx][ny] == null)) continue;

            jumpTo[dist] = true;
        }

        for(int j=0; j<3; j++) {
            if(!jumpTo[j]) continue;

            nx = x + dx[d] * (j+1);
            ny = y + dy[d] * (j+1);
            int store = map[nx][ny].n;

            // eat fish
            // 상어 있던 곳 비우기
            map[x][y] = null;
            // 물고기 자리로 이동하기
            shark.moveTo(nx, ny);
            // 물고기 먹기
            shark.haveAte += map[nx][ny].n;
            shark.d = map[nx][ny].d;
            map[nx][ny] = shark;
            // 물고기 잡아먹힌 표시하기
            eaten[store] = true;

            dfs(eaten);
            // 들어간 dfs에서 물고기가 이동했는데 그걸 복구 안해서 에러가 남

            // 상어 있던 곳에 다시 연결하기
            map[x][y] = shark;
            // 원래 자리로 이동하기
            shark.moveTo(x, y);
            // 물고기 뱉기
            shark.d = d;
            map[nx][ny] = fish16[store];
            shark.haveAte -= map[nx][ny].n;
            // 물고기 잡아먹힌 표시 풀기
            eaten[store] = false;
        }
    }

    private static void moveFish(Fish[][] map) {
        int x, y, nx, ny;
        for(int i=1; i<17; i++) {
            if(eaten[i]) continue;

            x = fish16[i].x;
            y = fish16[i].y;

            if(map[x][y]==null) {
                System.out.println("왜 이런 오류가");
            }

            for(int m=0; m<7; m++) {
                nx = x + dx[(fish16[i].d + m)%8];
                ny = y + dy[(fish16[i].d + m)%8];

                if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny ||
                        (shark.x==nx && shark.y==ny)) continue;

                fish16[i].d = (fish16[i].d + m)%8;

                if(map[nx][ny] == null) {
                    map[x][y] = null;
                    map[nx][ny] = fish16[i];
                    fish16[i].moveTo(nx, ny);
                } else {
                    temp = map[nx][ny];
                    map[nx][ny] = map[x][y];
                    map[x][y] = temp;

                    map[nx][ny].moveTo(nx, ny);
                    map[x][y].moveTo(x, y);
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Fish[] line: map) {
            for(Fish f: line) {
                if(f==null) sb.append("(0, 0) ");
                else sb.append(f.toString()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

class Fish {
    int x, y, n, d;

    public Fish() {}

    public Fish(int x, int y, int n, int d) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.d = d;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+n+", "+d+")";
    }
}

class Shark extends Fish {
    int haveAte;

    public Shark() {
        this.n = -1;
        this.haveAte = 0;
    }
}
