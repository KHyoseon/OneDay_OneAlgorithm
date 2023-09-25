import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, X, Y;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        arr = new int[H +X][W +Y];

        for(int i = 0; i< H +X; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< W +Y; j++) {
                arr[i][j] += Integer.parseInt(st.nextToken());
                if(i+X < H && j+Y < W) {
                    arr[i+X][j+Y] -= arr[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

}