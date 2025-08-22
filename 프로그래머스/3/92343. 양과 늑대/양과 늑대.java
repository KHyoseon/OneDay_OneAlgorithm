import java.util.*;
class Solution {
    ArrayList<Integer>[] tree;
    int sheepMax = 0;

    public int solution(int[] info, int[][] edges) {
        // 트리 구성
        tree = new ArrayList[info.length];
        
        for(int i=0, l=info.length; i<l; i++)
            tree[i] = new ArrayList<>();

        for(int[] edge: edges)
            tree[edge[0]].add(edge[1]);

        dfs(0, 0, 0, new boolean[info.length], info);

        return sheepMax;
    }

    private void dfs(int cur, int sheep, int wolves, boolean[] visited, int[] info) {
        // cur 노드 방문 가능?
        if(info[cur] == 0)  sheep++;
        else                wolves++;
        if(sheep <= wolves) return;
        // 방문 가능
        visited[cur] = true;
        sheepMax = Math.max(sheepMax, sheep);
        
        // 다음 방문지 찾음
        for(int next=0, l= info.length; next<l; next++) {
            if(!visited[next]) continue;
            // 부모까지는 갔는데
            for (int child : tree[next]){
                if(visited[child]) continue;
                // 자식 노드를 못 간 곳
                boolean[] newVisited = visited.clone();
                dfs(child, sheep, wolves, newVisited, info);
            }
        }
    }
}