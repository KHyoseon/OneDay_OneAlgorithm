import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, E, step1, step2;
    static ArrayList<HashMap<Integer, Integer>> maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        maps = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            maps.add(new HashMap<>());
        }

        int v1, v2, w;
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            maps.get(v1).put(v2, w);
            maps.get(v2).put(v1, w);
        }

        st = new StringTokenizer(br.readLine());
        step1 = Integer.parseInt(st.nextToken()) - 1;
        step2 = Integer.parseInt(st.nextToken()) - 1;

        int step = dijkstra(step1, step2);
        if(step == -1) {
            System.out.println(-1);
            return;
        }

        int sv1 = dijkstra(0, step1);
        int v2e = dijkstra(step2, N-1);
        int route1 = Integer.MAX_VALUE;
        if(sv1 != -1 && v2e != -1) route1 = sv1 + step + v2e;

        int sv2 = dijkstra(0, step2);
        int v1e = dijkstra(step1, N-1);
        int route2 = Integer.MAX_VALUE;
        if(sv2 != -1 && v1e != -1) route2 = sv2 + step + v1e;

        int answer = Math.min(route1, route2);
        System.out.println(answer == Integer.MAX_VALUE? -1: answer);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]));
        int[] length = new int[N];
        Arrays.fill(length, Integer.MAX_VALUE);

        length[start] = 0;
        for(int vertex: maps.get(start).keySet()) {
            length[vertex] = maps.get(start).get(vertex);
            pq.add(new int[]{vertex, length[vertex]});
        }

        HashMap<Integer, Integer> map;
        int[] near;

        while(!pq.isEmpty()) {
            near = pq.poll();
            if(near[0] == end) break;
            if(length[near[0]] == -1) continue;

            map = maps.get(near[0]);
            for(int i: map.keySet()) {
                if (length[i] > length[near[0]] + map.get(i)) {
                    length[i] = length[near[0]] + map.get(i);
                    pq.add(new int[]{i, length[i]});
                }
            }
            length[near[0]] = -1;
        }
        return length[end] == Integer.MAX_VALUE? -1: length[end];
    }

}