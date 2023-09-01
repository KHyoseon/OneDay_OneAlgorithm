import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BigInteger[][] dp = new BigInteger[101][51];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = N-1;
        int MIN = Integer.MAX_VALUE;
        int ll = l, rr = r;

        while(l < r) {
            if(arr[l] + arr[r] == 0) {
                ll = arr[l];
                rr = arr[r];
                break;
            } else if(arr[l] + arr[r] < 0) {
                if(Math.abs(arr[l] + arr[r]) < MIN) {
                    rr = arr[r];
                    ll = arr[l];
                    MIN = Math.abs(ll + rr);
                }
                l++;
            } else {
                if(Math.abs(arr[l] + arr[r]) < MIN) {
                    ll = arr[l];
                    rr = arr[r];
                    MIN = Math.abs(ll + rr);
                }
                r--;
            }
        }

        System.out.println(ll + " " + rr);

    }

}