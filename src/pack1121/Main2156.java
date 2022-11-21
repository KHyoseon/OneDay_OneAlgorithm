import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2156 {
    static int[][] memo;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] glasses = new int[N];

        for(int i=0; i<N; i++)
            glasses[i] = Integer.parseInt(br.readLine());

        // col:0 -> 첫번째로 선택됐을 떼 최댓값, col:1 -> 두번째로 선택됐을 때 최댓값
        // col == 앞에 선택되었으면 1, 아니면 0
        memo = new int[N+1][2];
        System.out.println(selectMax(glasses));
    }

    // 6 10 13 9 8 1

    private static int selectMax(int[] glasses) {
        memo[1][0] = memo[1][1] = glasses[0];

        for(int i=2; i<N+1; i++){
            memo[i][0] = Math.max(memo[i-1][0]+glasses[i], memo[i][1]);
            memo[i][1] = Math.max(memo[i - 1][0], memo[i - 1][1]);
        }

        return Math.max(memo[N][0], memo[N][1]);
    }
}
