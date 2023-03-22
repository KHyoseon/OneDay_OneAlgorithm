package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_16398_행성_연결 {
    static int N;
    static ArrayList<PriorityQueue<Edge>> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new PriorityQueue<>(Comparator.comparingInt(o -> o.w)));
        }

        // 그래프 구성
        StringTokenizer st;
        int w;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                w =  Integer.parseInt(st.nextToken());
                if(i==j) continue;
                edges.get(i).add(new Edge(j, w));
            }
        }

        // prim
        System.out.println(prim(0, 0));
    }

    private static int prim(int node, int sum) {
        if(edges.get(node).isEmpty()) return sum;

        Edge e = edges.get(node).peek();
        edges.get(node).remove(e);
        edges.get(e.n).remove(e);

        return prim(e.n, sum + e.w);
    }

    static class Edge {
        int n;
        int w;

        public Edge() {}

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
