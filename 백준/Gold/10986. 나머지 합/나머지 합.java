import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long sum = 0;
        long[] mod = new long[M];

        st = new StringTokenizer(br.readLine());

        for (int c=0; c<N; c++) {
            sum += Integer.parseInt(st.nextToken());
            int idx = (int)(sum % M);
            if(idx < 0) idx += M;
            mod[idx]++;
        }

        long cnt = mod[0];
        for(int m=0; m<M; m++) {
            cnt += (pickTwo(mod[m]));
        }

        System.out.println(cnt);
    }

    private static long pickTwo(long n) {
        return n*(n-1)/2;
    }
}