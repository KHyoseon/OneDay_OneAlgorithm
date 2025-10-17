import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int[][] matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        // floyd
        for(int step=0; step<N; step++) {
            for(int start=0; start<N; start++) {
                if(step == start) continue;

                for(int end=0; end<N; end++) {
                    if(step == end || start == end) continue;

                    if(matrix[start][end] > matrix[start][step] + matrix[step][end]) {
                        matrix[start][end] = matrix[start][step] + matrix[step][end];
                    }
                }
            }
        }

        visiteAllNodes(K, 0, matrix, 1<<K);

        System.out.println(MIN);
    }

    private static void visiteAllNodes(int cur, int sum, int[][] matrix, int flag) {
        if(flag == (1<<N)-1) {
            MIN = Math.min(MIN, sum);
            return;
        }

        if(sum >= MIN) return;

        for(int i=0; i<N; i++) {
            if((flag & 1<<i) != 0) continue;
            visiteAllNodes(i, sum + matrix[cur][i], matrix, flag|1<<i);
        }
    }
}