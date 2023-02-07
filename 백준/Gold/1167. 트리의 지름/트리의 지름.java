import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int N, max=0, node;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        // list 구성
        StringTokenizer st;
        int v1, v2, dist;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            while(true){
                v2 = Integer.parseInt(st.nextToken()) - 1;
                if(v2==-2) break;
                dist = Integer.parseInt(st.nextToken());
                list[v1].add(new Node(v2, dist));
            }
        }

        //임의의 노드(1)에서 부터 가장 먼 노드를 찾는다. 이때 찾은 노드를 node에 저장한다.
        visited = new boolean[N];
        dfs(1, 0);

        //node에서 부터 가장 먼 노트까지의 거리를 구한다.
        visited = new boolean[N];
        dfs(node, 0);

        System.out.println(max);
    }

    public static void dfs(int x, int len) {
        if(len > max) {
            max = len;
            node = x;
        }
        visited[x] = true;

        for(int i = 0; i < list[x].size(); i++) {
            Node n = list[x].get(i);
            if(visited[n.e] == false) {
                dfs(n.e, n.cost + len);
                visited[n.e] = true;
            }
        }

    }

    public static class Node {
        int e;
        int cost;

        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }
}