import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        String[] input;
        int[] nodes = new int[2];
        for(int i=1; i<N; i++) {
            input = br.readLine().split(" ");
            nodes[0] = Integer.parseInt(input[0]);
            nodes[1] = Integer.parseInt(input[1]);
            list[nodes[0]].add(nodes[1]);
            list[nodes[1]].add(nodes[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int[] parent = new int[N+1];
        while (!queue.isEmpty()) {
            int p = queue.poll();
            for(int c: list[p]) {
                if(parent[c] != 0) continue;
                parent[c] = p;
                queue.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }
}