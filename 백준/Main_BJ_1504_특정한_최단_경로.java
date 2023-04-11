package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1504_특정한_최단_경로 {

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
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            maps.get(v1).put(v2, w);
            maps.get(v2).put(v1, w);
        }

        st = new StringTokenizer(br.readLine());
        step1 = Integer.parseInt(st.nextToken());
        step2 = Integer.parseInt(st.nextToken());

        dijkstra();

    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]));
        int[] parent = new int[N];
        int[] length = new int[N];
        Arrays.fill(parent, 0);
        Arrays.fill(length, 1001);

        for(int vertex: maps.get(0).keySet()) {
            length[vertex] = maps.get(0).get(vertex);
            pq.add(new int[]{vertex, length[vertex]});
        }

        HashMap<Integer, Integer> W;
        int[] near;

        while(!pq.isEmpty()) {
            // near: 그래프에서 제일 가까운 노드
            near = pq.poll();
            if(near[0] == N-1) break;
            // 이미 그래프에 속해 있다면 length가 -1임
            if(length[near[0]] == -1) continue;
            length[near[0]] = -1;

            // 모든 노드를 탐색, 트리에서 i번 노드까지 near를 거치는 게 더 빠르면 갱신
            for(int i=0; i<N; i++) {
                W = maps.get(near[0]);
                // near와 i가 연결 안 됐으면 탐색 안함
                if(!W.containsKey(i)) continue;

                // i번 노드를 near를 거치는 게 더 빠르면 갱신
                if (length[i] > length[near[0]] + W.get(i)) {
                    length[i] = length[near[0]] + W.get(i);
                    parent[i] = near[0];
                    pq.add(new int[]{i, length[i]});
                }
            }
        }
    }


}
