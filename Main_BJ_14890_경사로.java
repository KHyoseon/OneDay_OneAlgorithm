import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14890_경사로 {
    static int[][] map;
    static int N, L, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        cnt = 0;

        map = new int[N][N];
        // map init
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 탐색
        for(int i=0; i<N; i++){
            cnt += funcForOneRow(i);
        }
        // 세로 탐색
        for(int j=0; j<N; j++){
            cnt += funcForOneCol(j);
        }

        System.out.println(cnt);
    }

    static public int funcForOneRow(int row){
        int start=0, til;
        int prevH=-1, prevL=0;
        int curH, curL;

        while(start < N) {
            for(til=start+1; til<N && map[row][start]==map[row][til]; til++);
            curH = map[row][start];
            curL = til-start;

            if(prevH != -1){
                if(prevH < curH && (curH-prevH != 1 || prevL < L)) return 0;
                if(prevH >= curH) {
                    if (prevH - curH != 1 || curL < L) return 0;
                    curL -= L;
                }
            }

            prevH = curH;
            prevL = curL;
            start = til;
        }
        return 1;
    }
    static public int funcForOneCol(int col){
        int start=0, til;
        int prevH=-1, prevL=0;
        int curH, curL;

        while(start < N) {
            for(til=start+1; til<N && map[start][col]==map[til][col]; til++);
            curH = map[start][col];
            curL = til-start;

            if(prevH != -1){
                if(prevH < curH && (curH-prevH != 1 || prevL < L)) return 0;
                if(prevH >= curH) {
                    if (prevH - curH != 1 || curL < L) return 0;
                    curL -= L;
                }
            }

            prevH = curH;
            prevL = curL;
            start = til;
        }
        return 1;
    }
}
