import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] cnt = new int[3];
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tear(0, 0, N);

        System.out.println(cnt[0] +"\n"+ cnt[1] +"\n"+ cnt[2]);
    }

    private static void tear(int r, int c, int n) {
        boolean fail = false;

        for(int i=r; i<r+n-1 && !fail; i++) {
            for (int j=c; j<c+n-1 && !fail; j++) {
                if(paper[i][j] != paper[i][j+1]
                        || paper[i][j] != paper[i+1][j]) {
                    fail = true;
                    break;
                }
            }
        }
        
        if(paper[r][c] != paper[r+n-1][c+n-1])
            fail = true;

        if(fail) {
            int Ndiv3 = n/3;
            tear(r, c, Ndiv3);
            tear(r, c + Ndiv3, Ndiv3);
            tear(r, c + 2 * Ndiv3, Ndiv3);

            tear(r + Ndiv3, c, Ndiv3);
            tear(r + Ndiv3, c + Ndiv3, Ndiv3);
            tear(r + Ndiv3, c + 2 * Ndiv3, Ndiv3);

            tear(r + 2 * Ndiv3, c, Ndiv3);
            tear(r + 2 * Ndiv3, c + Ndiv3, Ndiv3);
            tear(r + 2 * Ndiv3, c + 2 * Ndiv3, Ndiv3);
        } else {
            cnt[paper[r][c] + 1]++;
        }
    }
}