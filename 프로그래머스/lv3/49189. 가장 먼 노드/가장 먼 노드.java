import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int[] count;
    private boolean adj[][], visited[];
    private Queue<Integer> q = new LinkedList<>();
    
    public int solution(int n, int[][] edge) {
        count = new int[n+1];
        adj = new boolean[n+1][n+1];
        visited = new boolean[n+1];
    
        makeGraph(edge);

        q.add(1);
        visited[1]=true;
        bfs();

        int max=0;
        int answer = 0;
        for(int i=1; i<n+1; i++){
            if(max<count[i]){
                max=count[i];
                answer=0;
                answer++;
            } else if (max==count[i]){
                answer++;
            }
        }
        return answer;
    }
    private void bfs() {
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v=1; v<adj.length; v++){
                if(adj[u][v] && !visited[v]){
                    visited[v]=true;
                    count[v] = count[u]+1;
                    q.add(v);
                }
            }
        }
    }
    private void makeGraph(int[][] edge) {
        for(int i=0; i<edge.length; i++){
            int u=edge[i][0];
            int v=edge[i][1];
            adj[u][v]=true;
            adj[v][u]=true;
        }
    }
}