package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_19236_청소년_상어 {
    static Fish[] fish16 = new Fish[17];
    static Fish[][] map = new Fish[4][4];
    static Shark shark;
    static Fish temp;
    static int MAX = 0;

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

        dfs(0, 0);

        System.out.println(MAX);
    }

    private static void dfs(int x, int y) {
        // eat fish
        shark.eat(map[x][y]);
        fish16[map[x][y].n] = null;
        map[x][y] = shark;

        // move fish
        moveFish();

        // can move?
        int dist=1, d= shark.d;

        int nx = x, ny = y;
        for(; dist<4; dist++) {
            nx += dx[d];
            ny += dy[d];

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || (map[nx][ny] == null)) break;
        }

        dist--;
        if(dist == 0){
            // if no, return
            MAX = Math.max(MAX, shark.haveAte);
        }
        else {
            // if yes, start dfs again
            while (dist-- > 0) {
                nx = x + dx[d] * dist;
                ny = y + dy[d] * dist;
                dfs(nx, ny);
            }
        }
    }

    private static void moveFish() {
        int x, y, nx, ny;
        for(int i=1; i<17; i++) {
            if(fish16[i] == null) continue;

            x = fish16[i].x;
            y = fish16[i].y;

            for(int m=0; m<7; m++) {
                nx = x + dx[(fish16[i].d + m)%8];
                ny = y + dy[(fish16[i].d + m)%8];

                if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny ||
                        (shark.x==nx && shark.y==ny)) continue;

                fish16[i].d = (fish16[i].d + m)%8;

                if(map[nx][ny] == null) {
                    map[nx][ny] = fish16[i];
                    fish16[i].x = nx;
                    fish16[i].y = ny;
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

        /*StringBuilder sb = new StringBuilder();
        for(Fish[] line: map) {
            for(Fish f: line) {
                if(f==null) sb.append("(0, 0) ");
                else b.append(f.toString()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);*/
    }

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

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

    public Shark() {}
    public Shark(int x, int y, int n, int d, int haveAte) {
        super(x, y, n, d);
        this.haveAte = haveAte;
    }

    public void eat(Fish fish) {
        this.d = fish.d;
        this.haveAte += fish.n;
    }

    public void rewind(Fish fish) {
        this.haveAte -= fish.n;
    }
}
