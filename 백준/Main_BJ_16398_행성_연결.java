package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_16398_행성_연결 {
    static int N, SUM = 0;
    static boolean[] selected;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        // 그래프 구성
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[N];
        selected[0] = true;

        prim(0);
        System.out.println(SUM);
    }

    private static void prim(int node) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));

        boolean done = true;
        for (boolean s: selected) {
            if(!s) {
                done = false;
                break;
            }
        }

        if(done)    return;

        // 다음 이동 노드 간추리기
        for(int j=0; j<N; j++) {
            if(node == j || selected[j]) continue;
            queue.add(new int[] {j, graph[node][j]});
        }

        for(int[] next: queue) {
            if(selected[next[0]]) continue;
            selected[next[0]] = true;
            System.out.println(node + " 와 " + next[0] + " 연결, 가중치 "+next[1]);
            SUM += next[1];
            prim(next[0]);
        }
    }
    
    private void prim() {
        Stack<Integer> tree = new Stack<>();
        tree.add(0);

        int cur=0, node=0, next=0;
        int minWeight = Integer.MAX_VALUE, min = Integer.MAX_VALUE;

        // 새로 추가할 노드: node => cur과 가장 가까운 노드
        for(int j=0; j<N; j++) {
            if(j == 0) continue;
            if(weight[0][j] < minWeight) {
                minWeight = weight[0][j];
                node = j;
            }
        }

        int sum = 0;
         do {
            cur = tree.peek();
            minWeight = Integer.MAX_VALUE;

            // 새로 추가할 노드: node => cur과 가장 가까운 노드
            for(int j=0; j<N; j++) {
                if(j == cur) continue;
                if(weight[cur][j] < minWeight) {
                    minWeight = weight[cur][j];
                    node = j;
                }
            }

            // node와 가장 가까이 있는 tree 구성 노드 찾기
            minWeight = Integer.MAX_VALUE;
            for(int j=0; j<N; j++) {
                if(node==j && !tree.contains(j)) continue;
                if(weight[node][j] < minWeight) {
                    minWeight = weight[node][j];
                    next = j;
                }
            }

            sum += minWeight;
            tree.add(node);
        } while(tree.size() < N);
    }
}
