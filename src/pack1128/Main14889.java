import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main14889 {

    static int[][] S;
    static int N, min;
    static boolean[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        StringTokenizer st = null;
        int tmp;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                tmp = Integer.parseInt(st.nextToken());
                S[i][j] = tmp;
            }
        }

        min = Integer.MAX_VALUE;
        go();
        System.out.println(min);
    }

    private static void go() {
        for(int k=0; k<N; k++){
            team = new boolean[N];
            comb(k, N/2);
        }
    }

    private static void comb(int st, int cnt) {
        if(cnt<=0){
            int sum1=0, sum2=0;

            // 이중 for문 한번에 두 팀의 점수를 구할 수 있다.
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++) {
                    if(i==j)    continue;
                    if(team[i]&&team[j])            sum1 += S[i][j];
                    else if(!(team[i]||team[j]))    sum2 += S[i][j];
                }
            }

            min = min>Math.abs(sum1-sum2)? Math.abs(sum1-sum2): min;
            return;
        }

        for(int start = st; start<N; start++){
            team[start] = true;
            comb(start + 1, cnt - 1);
            team[start] = false;
        }
    }
}
