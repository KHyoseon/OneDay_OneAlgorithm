import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] map = new boolean[10][10];
    static int[] paper = {5, 5, 5, 5, 5, 5};
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 맵 구성
        int cnt=0;
        StringTokenizer st;
        for(int i=0; i<10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        int[] next = nextCoordi();
        dfs(next[0], next[1]);

        System.out.println(MIN>25? -1: MIN);
    }

    private static void dfs(int r, int c) {
        if(r==10 && c==10) {
            int sum = 0;
            for(int p: paper) {
                sum += (5 - p);
            }
            MIN = Math.min(MIN, sum);
        }

        int nr = r, nc = c;
        int height = 0, width = 0;
        for(; nr<10 && map[nr][c] && height<5; nr++) height++;
        for(; nc<10 && map[r][nc] && width<5; nc++) width++;

        for(int w=Math.min(height, width); w>0; w--) {
            if(paper[w]==0 || !canCovered(r, c, w)) continue;

            // w*w 크기 종이로 덮기
            paper[w]--;
            cover(r, c, w, false);

            // 다음 위치 이동
            int[] next = nextCoordi();
            dfs(next[0], next[1]);

            // 다시 원상복구
            paper[w]++;
            cover(r, c, w, true);
        }
    }

    private static int[] nextCoordi() {
        boolean find = false;
        for(int r=0; r<10; r++) {
            for(int c=0; c<10 && !find; c++) {
                if(map[r][c]) {
                    return new int[]{r, c};
                }
            }
        }
        return new int[]{10, 10};
    }

    private static boolean canCovered(int r, int c, int w) {
        for(int i=0; i<w; i++) {
            for(int j=0; j<w; j++) {
                if(!map[r+i][c+j]) return false;
            }
        }
        return true;
    }

    private static void cover(int r, int c, int w, boolean fill) {
        for(int i=0; i<w; i++) {
            for(int j=0; j<w; j++) {
                map[r + i][c + j] = fill;
            }
        }
    }

}