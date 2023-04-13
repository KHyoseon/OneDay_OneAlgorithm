import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start, end, money = 0;
    static int[][] W;
    static int[] length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        W = new int[N][N];
        length = new int[N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(W[i], Integer.MAX_VALUE);
        }
        Arrays.fill(length, Integer.MAX_VALUE);

        StringTokenizer st;
        int v1, v2, w;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            // v1->v2 행 버스가 여러개 주어질 경우 최솟값만 받음
            W[v1][v2] = Math.min(W[v1][v2], w);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        if(start == end) {
            System.out.println(0);
            return;
        }

        dijkstra();
        System.out.println(length[end]);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1->o1[1]));

        // start 기준으로 length 초기화
        for(int i=0; i<N; i++) {
            if(i == start) continue;
            length[i] = W[start][i];
            pq.add(new int[]{i, length[i]});
        }
        length[start] = -1;

        int[] near;

        while(!pq.isEmpty()) {
            // near: 그래프에서 제일 가까운 노드
            near = pq.poll();
            money += near[1];
            if(near[0] == end) break;

            // 이미 그래프에 속해 있다면 length가 -1임
            if(length[near[0]] == -1) continue;

            // 모든 노드를 탐색, 트리에서 i번 노드까지 near를 거치는 게 더 빠르면 갱신
            for(int i=0; i<N; i++) {
                // near와 i가 연결 안 됐으면 탐색 안함
                if(near[0] == i || W[near[0]][i] == Integer.MAX_VALUE) continue;

                // i번 노드로 갈 때, near를 거치는 게 더 빠르면 갱신
                if (length[i] > length[near[0]] + W[near[0]][i]) {
                    length[i] = length[near[0]] + W[near[0]][i];
                    pq.add(new int[]{i, length[i]});
                }
            }
            length[near[0]] = -1;
        }
    }
}