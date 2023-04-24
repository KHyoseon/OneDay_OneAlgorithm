import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<String, Integer> word = new HashMap<>();
        for(int i=0; i<N; i++) {
            String key = br.readLine();
            if(key.length() < M) continue;
            word.put(key, word.getOrDefault(key, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>(((o1, o2) -> word.get(o1)==word.get(o2)? (o1.length()==o2.length()? o1.compareTo(o2): o2.length()-o1.length()): word.get(o2)-word.get(o1)));
        queue.addAll(word.keySet());

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            sb.append(queue.poll()).append("\n");
        }

        System.out.print(sb);
    }
}