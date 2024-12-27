import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[] virus;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        virus = new boolean[N];
        for(int i=0; i<N; i++)
            graph.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0])-1;
            int v2 = Integer.parseInt(input[1])-1;
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        Queue<Integer> queue = new LinkedList<>();
        virus[0] = true;
        queue.add(0);

        int cnt = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int node: graph.get(cur)) {
                if(virus[node]) continue;
                cnt++;
                virus[node] = true;
                queue.add(node);
            }
        }

        System.out.println(cnt);
    }

}