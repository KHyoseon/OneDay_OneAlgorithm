import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new boolean[R][C];
        boolean allBlank = true;

        for(int i=0; i<R; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                map[i][j] = line[j] == '1';
                if(map[i][j]) allBlank = false;
            }
        }

        if(allBlank) {
            System.out.println("0");
            return;
        }

        for(int size=Math.min(R, C)/2; 0<size; size--) {
            for(int r=size; r<R-size; r++) {
                for(int c=size; c<C-size; c++) {
                    if(!isDiamond(r, c, size)) continue;
                    System.out.println(size+1);
                    return;
                }
            }
        }

        System.out.println("1");
    }

    private static boolean isDiamond(int r, int c, int size) {
        int nr = r - size;

        if(!map[nr][c]) return false;
        for(int p=1; p<=size; p++) {
            if(!map[nr+p][c-p]) return false;
            if(!map[nr+p][c+p]) return false;
        }

        nr = r + size;

        if(!map[nr][c]) return false;
        for(int p=1; p<size; p++) {
            if(!map[nr-p][c-p]) return false;
            if(!map[nr-p][c+p]) return false;
        }

        return true;
    }

}