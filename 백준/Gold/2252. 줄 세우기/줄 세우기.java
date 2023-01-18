import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N + 1);
        for(int i=0; i<N+1; i++){
            list.add(new ArrayList<>());
        }

        String[] input;
        int[] vertex = new int[2];

        int[] indegree = new int[N+1];

        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            vertex[0] = Integer.parseInt(input[0]);
            vertex[1] = Integer.parseInt(input[1]);
            
            list.get(vertex[0]).add(vertex[1]);
            indegree[vertex[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<N+1; i++)
            if(indegree[i]==0) queue.add(i);

        int student;
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            student = queue.poll();
            sb.append(student).append(' ');
            for(int next: list.get(student)) {
                indegree[next]--;
                if(indegree[next]==0) queue.add(next);
            }
        }

        System.out.println(sb);
    }
}