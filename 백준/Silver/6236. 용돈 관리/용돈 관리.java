import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        spend = new int[N];

        int MAX = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            spend[i] = Integer.parseInt(br.readLine());
            MAX = Math.max(MAX, spend[i]);
        }

        int l = MAX, r = (int)1e9;
        while (l < r) {
            int mid = (l + r) / 2;
            if (func(mid))
                r = mid;
            else l = mid + 1;
        }

        System.out.println(l);
    }

    static int N, M;
    static int[] spend;

    private static boolean func(int K) {
        int remain = K;
        int m = 1;

        for(int i=0; i<N; i++) {
            int money = spend[i];
            if(remain >= money) {
                remain -= money;
            } else if(m < M && K >= money) {
                remain = K-money;
                m++;
            } else return false;
        }
        return m <= M;
    }
}