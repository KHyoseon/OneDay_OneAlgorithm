import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[] M, Z, empty;
    static char missing;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i = 0; i< R; i++) {
            String input = br.readLine();
            if (input.contains("M")) {
                M = new int[] {i, input.indexOf("M")};
            }
            if (input.contains("Z")) {
                Z = new int[]{i, input.indexOf("Z")};
            }
            map[i] = input.toCharArray();
        }

        for(int d=0; d<4; d++) {
            if(canGo(M[0], M[1], d)) {
                step(M[0]+dr[d], M[1]+dc[d], (d+2)%4);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(empty[0]+1).append(" ").append(empty[1]+1).append(" ").append(missing);
        System.out.println(sb);
    }

    static int[] dr ={-1, 0, 1, 0};
    static int[] dc ={0, 1, 0, -1};

    private static void step(int r, int c, int in) {
        // 현재 위치 (r, c)에서 in 방향의 입구에서 왔을 때 out 방향의 출구를 통해 다음 칸으로 이동한다
        int out = in;

        int flag = 1;
        if(r==5 && c==3) {
            flag = 2;
        }

        if(r < 0 || r >= R || c < 0 || c >= C) return;

        switch (map[r][c]) {
            case '+':
            case '-':
            case '|':
                out = (in+2)%4;
                break;
            case '1':
                if (in == 2) {
                    out = 1;
                } else if (in == 1) {
                    out = 2;
                }
                break;
            case '2':
                if (in == 0) {
                    out = 1;
                } else if (in == 1) {
                    out = 0;
                }
                break;
            case '3':
                if (in == 0) {
                    out = 3;
                } else if (in == 3) {
                    out = 0;
                }
                break;
            case '4':
                if (in == 2) {
                    out = 3;
                } else if (in == 3) {
                    out = 2;
                }
                break;
            case '.':
                empty = new int[]{r, c};
                missing = findCharOfBlank(in);
                return;
        }

        step(r+dr[out], c+dc[out], (out+2)%4);
    }

    private static char findCharOfBlank(int in) {
        Set<Integer> possible = new HashSet<>();
        for(int d=0; d<4; d++) {
            // 현재 빈칸에서 d 방향으로 갔을 때
            if(d==in) continue;
            if(canGo(empty[0], empty[1], d)) {
                possible.add(d);
            }
        }

        if(possible.size()>=3) {
            return '+';
        }
        
        for(int d: possible){
            if(in==0 && d==2 || in==2 && d==0) return '|';
            if(in==1 && d==3 || in==3 && d==1) return '-';
            if(in==1 && d==2 || in==2 && d==1) return '1';
            if(in==0 && d==1 || in==1 && d==0) return '2';
            if(in==0 && d==3 || in==3 && d==0) return '3';
            if(in==2 && d==3 || in==3 && d==2) return '4';
        }

        return ' ';
    }

    private static boolean canGo(int r, int c, int d) {
        boolean ret = false;
        int nr = r + dr[d];
        int nc = c + dc[d];

        if(nr < 0 || nr >= R || nc < 0 || nc >= C) return false;

        switch (map[nr][nc]) {
            case '+':
                ret = true;
                break;
            case '|':
                if (d == 0 || d == 2) ret = true;
                break;
            case '-':
                if (d == 1 || d == 3) ret = true;
                break;
            case '1':
                if (d == 0 || d == 3) ret = true;
                break;
            case '2':
                if (d == 2 || d == 3) ret = true;
                break;
            case '3':
                if (d == 1 || d == 2) ret = true;
                break;
            case '4':
                if (d == 0 || d == 1) ret = true;
                break;
        }

        return ret;
    }
}