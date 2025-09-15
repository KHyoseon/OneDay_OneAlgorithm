import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        boolean[] even = new boolean[N];

        int i=0;
        input = br.readLine().split(" ");
        for(String number: input) {
            int n = Integer.parseInt(number);
            even[i++] = n%2 == 0;
        }

        int left = 0;
        int del = K;
        int MAX = 0;

        for(; left < N && !even[left]; left++);
        int right = left + 1;

        while(left < N) {

            for(; right < N; right++) {
                if(even[right]) continue;
                if(del == 0) break;
                else --del;
            }

            MAX = Math.max(MAX, right - left - (K - del));

            for(; left < N && even[left]; left++);

            for(; left < N && !even[left]; left++) {
                ++del;
            }
        }

        System.out.println(MAX);
    }
}