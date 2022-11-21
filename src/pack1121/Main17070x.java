import java.io.*;
import java.util.Arrays;

public class Main17070 {

    static int N;
    static int[][] memo, direct;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        memo = new int[N][N];
        map = new char[N][N];

        for(int i=0; i<N; i++)
           Arrays.fill(memo[i], -1);

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }

        // direction
        direct[0][0] = 0;   direct[0][1] = 1;
        direct[1][0] = 1;   direct[1][1] = 1;
        direct[2][0] = 1;   direct[2][1] = 0;

        map[0][0] = map[0][1] = '1';

        bw.write(bfs());
        bw.flush();

    }

    private static int bfs(){
        return bfs(0, 1);
    }

    private static int bfs(int r, int c) {
//        if(memo[r][c] != -1) return memo[r][c];
        int ret = 0;

        for(int d=0; d<3; d++){
            int next[] = new int[] {r+direct[d][0], c+direct[d][1]};

        }

        return ret;
    }
}
