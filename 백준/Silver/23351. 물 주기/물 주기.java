import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // N개의 화분, 초기 수분량 K, A개의 화분에 B만큼 물을 줌
    static int N, K, A, B, DAY=0;
    static int[] plants;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        A = Integer.parseInt(input[2]);
        B = Integer.parseInt(input[3]);

        plants = new int[N];
        Arrays.fill(plants, K);

        simulate();

        System.out.println(DAY);
    }

    private static void simulate() {
        boolean done = false;
        int loop=-1;

        do {
            ++DAY;
            ++loop;

            int plant = (A*loop)%N;
            // water
            for(int i=0; i<A; i++) {
                plants[plant+i] += B;
            }
            // time past
            for(int i=0; i<N; i++) {
                plants[i]--;
                if(plants[i] <= 0) {
                    done = true;
                    break;
                }
            }
        } while(!done);
    }
}