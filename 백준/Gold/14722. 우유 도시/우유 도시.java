import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] max;
    static int[][] map;
    static Check[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        max = new int[2*N];
        // 0,0 에서 r,c까지 왔을 때 얻을 수 있는 우유의 최대 개수
        map = new int[N][N];
        check = new Check[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check[0][0] = new Check(map[0][0] == 0 ? 1 : 0, map[0][0] == 0 ? 1 : 0);

        for(int sum=1; sum<2*N; sum++) {
            for(int r=0; r<Math.min(sum+1, N); r++) {
                int c = sum - r;
                if (c >= N) continue;

                check[r][c] = new Check();
                if (0 < r) {
                    if (check[r-1][c].expectNext==map[r][c]) {
                        check[r][c].milk = check[r-1][c].milk + 1;
                        check[r][c].expectNext = (check[r-1][c].expectNext + 1) % 3;
                    } else {
                        check[r][c].milk = check[r-1][c].milk;
                        check[r][c].expectNext = check[r-1][c].expectNext;
                    }
                }
                if (0 < c) {
                    if (check[r][c-1].expectNext==map[r][c] && check[r][c].milk < check[r][c-1].milk+1) {
                        check[r][c].milk = check[r][c-1].milk + 1;
                        check[r][c].expectNext = (check[r][c-1].expectNext + 1) % 3;
                    } else if (check[r][c].milk < check[r][c-1].milk) {
                        check[r][c].milk = check[r][c-1].milk;
                        check[r][c].expectNext = check[r][c-1].expectNext;
                    }
                }
            }
        }

        System.out.println(check[N-1][N-1].milk);
    }

}

class Check {
    int milk;
    int expectNext;

    public Check() {}

    public Check(int milk, int expectNext) {
        this.milk = milk;
        this.expectNext = expectNext;
    }
}