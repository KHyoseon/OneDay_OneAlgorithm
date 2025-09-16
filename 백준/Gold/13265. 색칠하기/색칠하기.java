import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for(int i=0; i<N+1; i++) {
                graph.add(new ArrayList());
            }

            int[] nodes = new int[N + 1]; // 0안씀
            Arrays.fill(nodes, -1);

            for(int i=0; i<M; i++) {
                input = br.readLine().split(" ");
                int v1 = Integer.parseInt(input[0]);
                int v2 = Integer.parseInt(input[1]);
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            boolean[] visited = new boolean[N + 1];
            boolean impossible = false;
            Queue<int[]> queue = new LinkedList<>();

            for(int i=1; i<N+1 && !impossible; i++) {
                if(visited[i]) continue;

                nodes[i] = 0;
                queue.add(new int[]{i, nodes[i]});

                while (!queue.isEmpty() && !impossible) {
                    int[] cur = queue.poll();
                    visited[cur[0]] = true;
                    // 나랑 연결돼 있는 애들
                    for (int next : graph.get(cur[0])) {
                        if (visited[next]) continue;
                        // 나랑 연결돼 있는 애들 중에 나랑 같은 색이 있음
                        if (nodes[next] == cur[1]) {
                            impossible = true;
                            break;
                        }
                        if (nodes[next] == -1)
                            nodes[next] = (cur[1] + 1) % 2;
                        // 연결된 애들 큐에 넣음
                        queue.add(new int[]{next, nodes[next]});
                    }
                }
            }

            if(impossible)
                sb.append("impossible").append("\n");
            else sb.append("possible").append("\n");
            --T;
        }

        System.out.print(sb);
    }
}