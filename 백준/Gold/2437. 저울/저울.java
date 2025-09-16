import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] choo = new int[N];
        int[] sum = new int[N + 1];

        for(int i=0; i<N; i++) {
            choo[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(choo);

        for(int i=0; i<N; i++) {
            sum[i+1] = sum[i] + choo[i];
            if (choo[i] - sum[i] <= 1) continue;
            System.out.println(sum[i] + 1);
            return;
        }

        System.out.println(sum[N] + 1);
    }
}