import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i=0; i<n+1; i++) {
            map.add(new ArrayList<>());
        }

        for(int[] road: roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }

        Queue<Integer> pq = new LinkedList<>();
        int[] dist = new int[n+1];
        int INF = n*2;

        /*for(int i=0; i<=n; i++) {
            if(i==destination) continue;
            if(map.get(destination).contains(i)){
                dist[i] = 1;
                pq.add(i);
            }
            else dist[i] = INF;
        }*/
        Arrays.fill(dist, INF);
        dist[destination] = 0;

        pq.add(destination);

        boolean[] selected = new boolean[n + 1];
//        selected[destination] = true;

        while(!pq.isEmpty()) {
            int near = pq.poll();
            if(selected[near]) continue;
            selected[near] = true;

            for(int node: map.get(near)) {
                if(node == near) continue;
                if (dist[node] > dist[near] + 1) {
                    dist[node] = dist[near] + 1;
                    pq.add(node);
                }
            }
        }

        int[] answer = new int[sources.length];

        int i=0;
        for(int c: sources) {
            answer[i++] = dist[c]>= INF? -1: dist[c];
        }

        return answer;
    }
}