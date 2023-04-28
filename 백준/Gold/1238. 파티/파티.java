import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, INF = Integer.MAX_VALUE;
    static int[][] map, reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][N];
        reverse = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(map[i], INF);
            Arrays.fill(reverse[i], INF);
        }

        int v1, v2, time;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            time = Integer.parseInt(st.nextToken());

            reverse[v2][v1] = map[v1][v2] = time;
        }

        int[] dist1 = dijkstra(map);
        int[] dist2 = dijkstra(reverse);

        int answer = 0;
        for(int i=0; i<N; i++) {
            if(i==X) continue;
            if(answer < dist1[i] + dist2[i]) {
                answer = dist1[i] + dist2[i];
            }
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(int[][] map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));

        boolean[] involoved = new boolean[N];
        int[] dist = new int[N];

        Arrays.fill(dist, INF);

        for(int i=0; i<N; i++) {
            dist[i] = map[X][i];
            pq.add(new int[]{i, map[X][i]});
        }

        while(!pq.isEmpty()) {
            int[] near = pq.poll();

            if(involoved[near[0]]) continue;
            involoved[near[0]] = true;

            for(int i=0; i<N; i++) {
                if(i==near[0] || map[near[0]][i] == Integer.MAX_VALUE) continue;

                if(dist[near[0]] + map[near[0]][i] < dist[i]) {
                    dist[i] = dist[near[0]] + map[near[0]][i];
                    pq.add(new int[]{i, dist[i]});
                }
            }
        }

        return dist;
    }
}