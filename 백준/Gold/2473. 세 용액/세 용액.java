import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Long[] arr = new Long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long MIN = Long.MAX_VALUE;
        int m = 1;
        Long[] answer = new Long[3];

        while(m < N-1) {
            int l = 0, r = N-1;
            long sum = 0;
            long abs = 0;
            long min = Long.MAX_VALUE;
            Long[] temp = new Long[3];

            while(l<m && m<r) {
                sum = arr[l] + arr[m] + arr[r];
                abs = Math.abs(sum);

                if(sum == 0) {
                    System.out.println(arr[l] + " " + arr[m]  + " " + arr[r]);
                    return;
                }

                if(min > abs) {
                    min = abs;
                    temp[0] = arr[l];
                    temp[1] = arr[m];
                    temp[2] = arr[r];
                }

                // l, r 포인터 이동
                if (sum < 0) l++;
                else r--;
            }

            if(MIN > min) {
                MIN = min;
                answer[0] = temp[0];
                answer[1] = temp[1];
                answer[2] = temp[2];
            }
            m++;
        }

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}