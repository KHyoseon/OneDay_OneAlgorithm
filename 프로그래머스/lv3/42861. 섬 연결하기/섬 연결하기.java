import java.util.*;

class Solution {
    HashMap<Integer, Integer> parent = new HashMap<>();
    HashSet<Integer> vertexs = new HashSet<>();
    int answer = 0;

    public int solution(int n, int[][] costs) {
        // kruskal
        // 일단 weight 오름차 순으로 정렬
        Arrays.sort(costs, (Comparator.comparingInt(o -> o[2])));
        for(int[] cost: costs) {
            vertexs.add(cost[0]);
            vertexs.add(cost[1]);
        }

        for (int vertex: vertexs) {
            parent.put(vertex, vertex);
        }

        int nodes = 0;
        for(int[] cost: costs) {
            if(nodes == n-1) break;
            // 하나씩 추가하면서 사이클 생기는지 확인 (union find)
            if(union(cost[0], cost[1])){
                nodes++;
                answer += cost[2];
            }
        }

        return answer;
    }

    public boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 == p2) return false;
        parent.put(p2, p1);
        return true;
    }

    private int find(int v) {
        if(v == parent.get(v)) return v;
        int p = find(parent.get(v));
        parent.put(v, p);
        return p;
    }
}