import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, cnt = 0;
    static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queen = new int[N];

        // input: 다음으로 탐색할 row
        backTracking(0);

        System.out.print(cnt);
    }

    private static void backTracking(int row) {
        if(row == N) {
            cnt++;
            return;
        }

        for(int col=0; col<N; col++) {
            boolean possible = true;
            for(int q=0; q<row; q++) {
                if(queen[q] == col || Math.abs(q - row) == Math.abs(queen[q] - col)){
                    possible = false;
                    break;
                }
            }
            if(!possible) continue;
            queen[row] = col;
            backTracking(row + 1);
        }
    }

}