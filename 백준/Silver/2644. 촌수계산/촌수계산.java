import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int v1 = Integer.parseInt(input[0])-1;
        int v2 = Integer.parseInt(input[1])-1;

        int M = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        for(int i=0; i<N; i++)
            graph.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0])-1;
            int n2 = Integer.parseInt(input[1])-1;
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        Queue<int[]> queue = new LinkedList<>();
        visited[v1] = true;
        queue.add(new int[]{v1, 0});

        int[] cur = {0, -1};
        while(!queue.isEmpty()) {
            cur = queue.poll();

            if(cur[0]==v2) break;

            for(int node: graph.get(cur[0])) {
                if(visited[node]) continue;
                visited[node] = true;
                queue.add(new int[]{node, cur[1]+1});
            }
        }

        if(cur[0] != v2) cur[1] = -1;

        System.out.println(cur[1]);
    }

}