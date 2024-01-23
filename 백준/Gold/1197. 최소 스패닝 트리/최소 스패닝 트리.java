import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int v1, v2, w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V];
        for(int i=0; i<V; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);

        int v1, v2, w;
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());

            queue.add(new Edge(v1, v2, w));
        }

        int cnt = 0;
        int sum = 0;
        for(int i=0; i<E && cnt<V-1; i++) {
            Edge edge = queue.poll();

            if(find(edge.v1) == find(edge.v2)) continue;

            union(edge);
            cnt++;
            sum += edge.w;
        }

        System.out.println(sum);
    }

    private static int find(int v) {
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    private static void union(Edge edge) {
        int p1 = find(edge.v1);
        int p2 = find(edge.v2);
        parent[p1] = p2;
    }
}