import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] bus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        bus = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(bus[i], 100000000);
        }

        int v1, v2, cost;
        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            cost = Integer.parseInt(st.nextToken());
            bus[v1][v2] = Math.min(bus[v1][v2], cost);
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for(int[] line: bus) {
            for(int l: line) {
                sb.append(l>=100000000? "0": l).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void floyd() {
        for(int step=0; step<N; step++) {
            for(int start=0; start<N; start++) {
                if(start==step) continue;
                for(int end=0; end<N; end++) {
                    if(start==end || step==end) continue;
                    if(bus[start][end] > bus[start][step] + bus[step][end]) {
                        bus[start][end] = bus[start][step] + bus[step][end];
                    }
                }
            }
        }
    }
}