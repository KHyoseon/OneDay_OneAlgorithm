import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_14719_빗물 {
    static int H, W;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        int w=0, h=0;
        while(st.hasMoreTokens()){
            h = Integer.parseInt(st.nextToken());
            for(int i=0; i<h; i++){
                map[i][w] = true;
            }
            w++;
        }

        int open, sum=0;
        for(int r=0; r<H; r++){
            open = -1;
            for(int c=0; c<W; c++) {
                if (map[r][c]){
                    if(open != -1)
                        sum += (c - open - 1);
                    open = c;
                }
            }
        }

        System.out.println(sum);
    }
}