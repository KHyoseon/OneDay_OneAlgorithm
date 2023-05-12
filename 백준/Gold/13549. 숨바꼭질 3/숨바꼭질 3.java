import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, MAX, INF = 100001;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        MAX = Math.abs(K - N);
        visited = new boolean[INF];

        if(K > N) bfs();
        System.out.println(MAX);
    }
    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        visited[N] = true;

        int[] cur;
        while(!queue.isEmpty()) {
            cur = queue.poll();

            if(cur[1] >= MAX) break;
            if(cur[0] >= 2*K) continue;

            if(cur[0]<=K && cur[0]*2<INF && !visited[cur[0]*2]) {
                if(cur[0]*2 == K){
                    MAX = Math.min(MAX, cur[1]);
                    break;
                }
                    visited[cur[0] * 2] = true;
                    queue.add(new int[]{cur[0] * 2, cur[1]});
            }
            if(2<cur[0] && !visited[cur[0]-1]) {
                if(cur[0]-1 == K){
                    MAX = Math.min(MAX, cur[1]+1);
                    break;
                }
                    visited[cur[0] - 1] = true;
                    queue.add(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if(cur[0]+1<INF && !visited[cur[0]+1]){
                if(cur[0]+1 == K){
                    MAX = Math.min(MAX, cur[1]+1);
                    break;
                }
                    visited[cur[0] + 1] = true;
                    queue.add(new int[]{cur[0] + 1, cur[1] + 1});

            }
        }
    }

}