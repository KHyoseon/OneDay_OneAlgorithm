import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        while(TC > 0) {
            String[] input = br.readLine().split(" ");
            int tc = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

           int answer = periodOf(m);

            sb.append(tc).append(" ").append(answer).append("\n");
            --TC;
        }
        System.out.print(sb);
    }

    private static int periodOf(int m) {
        int period = 1;
        int a = 1, b = 2;
        while(true) {
            if(a == 1 && b == 1) break;
            int c = (a + b) % m;
            a = b;
            b = c;
            ++period;
        }
        return period;
    }

    private static int isPowOf(int n, int N) {
        int cnt = 0;
        while(n > 1) {
            if (n % N != 0)  return 0;
            n /= N;
            ++cnt;
        }
        return cnt;
    }
}