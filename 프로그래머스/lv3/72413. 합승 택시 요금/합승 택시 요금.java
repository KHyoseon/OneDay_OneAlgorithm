import java.util.*;
class Solution {
    // n: 총 노드 개수, s: 시작 노드, a,b: 어피치, 무지 노드
    int matrix[][];
    final int INF = 999999;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        matrix = new int[n][n];

        for(int r=0; r<n; r++)
            Arrays.fill(matrix[r], INF);

        for(int[] node: fares)
            matrix[node[0]-1][node[1]-1] = matrix[node[1]-1][node[0]-1] = node[2];

        floyd(n);

        int min = Integer.MAX_VALUE;
        for(int k=0; k<n; k++)
            min = Math.min(min, matrix[k][s-1] + matrix[k][a - 1] + matrix[k][b - 1]);

        return min;

        /*int[] distS = dijkstra(Arrays.copyOf(matrix[s - 1], n), n, s-1);
        int[] distA = dijkstra(Arrays.copyOf(matrix[a - 1], n), n, a-1);
        int[] distB = dijkstra(Arrays.copyOf(matrix[b - 1], n), n, b-1);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        distS[s-1] = distA[a-1] = distB[b-1] = 0;

        for(int i=0; i<n; i++){
            pq.add(distS[i] + distA[i] + distB[i]);
        }

        return pq.peek();*/
    }

    private void floyd(int n){
        for(int k=0; k<n; k++){
            for(int s=0; s<n; s++){
                if(k==s) continue;
                for(int f=0; f<n; f++){
                    if(k==f || s==f) continue;
                    if(matrix[s][f] > matrix[s][k] + matrix[k][f]){
                        matrix[s][f] = matrix[s][k] + matrix[k][f];
                    }
                }
            }
        }

        for(int i=0; i<n; i++)
            matrix[i][i] = 0;
    }

    private int[] dijkstra(int[] dist, int n, int s){
        int[] shortest;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(dist[i] == INF) continue;
            pq.add(new int[]{i, dist[i]});
        }

        while(!pq.isEmpty()){
            shortest = pq.poll();
            if(visited[shortest[0]]) continue;
            visited[shortest[0]] = true;

            for(int j=0; j<n; j++) {
                if(matrix[shortest[0]][j] == INF || j==s) continue;
                if (dist[j] > dist[shortest[0]] + matrix[shortest[0]][j]) {
                    dist[j] = dist[shortest[0]] + matrix[shortest[0]][j];
                    pq.add(new int[]{j, dist[j]});
                }
            }
        }
        return dist;
    }
}