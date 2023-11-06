import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String X = " " + br.readLine();
        String Y = " " + br.readLine();
        int xl = X.length();
        int yl = Y.length();

        int[][] LCS = new int[X.length()][Y.length()];

        // init
        for(int i=0; i<xl; i++) {
            LCS[i][0] = 0;
        }
        for(int i=0; i<yl; i++) {
            LCS[0][i] = 0;
        }

        // 모든 LCS 탐색
        for(int r=1; r<xl; r++) {
            for(int c=1; c<yl; c++) {
                if(X.charAt(r) == Y.charAt(c)) {
                    LCS[r][c] = LCS[r-1][c-1] + 1;
                } else {
                    LCS[r][c] = Math.max(LCS[r-1][c], LCS[r][c-1]);
                }
            }
        }

        System.out.println(LCS[xl-1][yl-1]);
    }
}