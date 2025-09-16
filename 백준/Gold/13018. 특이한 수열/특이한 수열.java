import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        if(K == N) {
            System.out.println("Impossible");
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(N-K).append(" ");
        for(int i=0; i<N-K-1; i++) {
            sb.append(i + 1).append(" ");
        }
        for(int i=N-K; i<N; i++) {
            sb.append(i + 1).append(" ");
        }

        System.out.println(sb);
    }
}