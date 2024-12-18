import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] depth;
    static HashMap<Integer, ArrayList<Integer>> children = new HashMap<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        depth = new int[N];

        Arrays.fill(depth, -1);

        int A, B;
        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            A = Integer.parseInt(input[0]) - 1;
            B = Integer.parseInt(input[1]) - 1;
            children.computeIfAbsent(B, key-> new ArrayList<>());
            children.get(B).add(A);
        }

        for(int i=0; i<N; i++) {
            depth[i] = dp(i);
        }

        System.out.println(Arrays.toString(depth)
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }

    private static int dp(int vertex) {
        if(depth[vertex] != -1) return depth[vertex];
        if(!children.containsKey(vertex)) return 1;

        for (int child : children.get(vertex)) {
            depth[vertex] = Math.max(depth[vertex], dp(child) + 1);
        }

        return depth[vertex];
    }

}