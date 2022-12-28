import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_BJ_9375_패션왕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        int N, answer;

        HashMap<String, Integer> hash;
        for(int t=0; t<TC; t++){
            N = Integer.parseInt(br.readLine());
            hash = new HashMap<>();

            String[] input;
            for(int i=0; i<N; i++) {
                input = br.readLine().split(" ");
                if(hash.containsKey(input[1]))
                    hash.replace(input[1], hash.get(input[1])+1);
                else
                    hash.put(input[1], 1);
            }

            answer = 1;
            for(String key: hash.keySet()){
                answer *= (hash.get(key) + 1);
            }

            sb.append(answer - 1).append('\n');
        }

        System.out.println(sb);
    }
}
