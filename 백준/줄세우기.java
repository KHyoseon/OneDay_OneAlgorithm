package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main_BJ_2252_줄_세우기 {
    static ArrayList<ArrayList<Integer>> list;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N);
        for(int i=0; i<N; i++){
            list.add(new ArrayList<>());
        }

        String[] input;
        int[] vertex = new int[2];

        int[] indegree = new int[N];

        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            vertex[0] = Integer.parseInt(input[0]) -1;
            vertex[1] = Integer.parseInt(input[1]) -1;
            // 앞인 애가 뒤의 애를 가리킴
            list.get(vertex[0]).add(vertex[1]);
            indegree[vertex[1]]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> indegree[o]));
        for(int i=0; i<N; i++){
            queue.add(i);
        }

        int student;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            // 한 애가 나오면 걔가 가르키는 노드에서 얘를 뺌
            student = queue.poll();
            sb.append(student+1).append(' ');
            for(int next: list.get(student)) {
                indegree[next]--;
            }
        }

        System.out.println(sb);
    }
}
