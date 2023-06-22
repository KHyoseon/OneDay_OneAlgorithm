import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E, INF = Integer.MAX_VALUE;
    static int[][] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        adj = new int[V][V];
        for(int i=0; i<V; i++) {
            Arrays.fill(adj[i], INF);
        }

        int a, b, c;
        StringTokenizer st;

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());

            adj[a][b] = c;
        }

        for(int step=0; step<V; step++) {
            for(int start=0; start<V; start++) {
                if(step == start) continue;
                for(int end=0; end<V; end++) {
                    if(step == end) continue;
                    if(adj[start][step]!=INF && adj[step][end]!=INF)
                        adj[start][end] = Math.min(adj[start][end], adj[start][step]+adj[step][end]);
                }
            }
        }

        int max = Integer.MAX_VALUE;
        for(int i=0; i<V; i++) {
            max = Math.min(max, adj[i][i]);
        }

        System.out.println(max==INF? -1: max);
    }
}