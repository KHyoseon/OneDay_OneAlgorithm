import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long MAX = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 누적합
        long[][] arr = new long[N+1][M+1];

        int input;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][1] = Integer.parseInt(st.nextToken());
            for(int j=2; j<=M; j++) {
                arr[i][j] = arr[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int j=1; j<=M; j++) {
            for(int i=2; i<=N; i++) {
                arr[i][j] += arr[i-1][j];
            }
        }

        simulate(arr, N, M);

        System.out.println(MAX);
    }

    private static void simulate(long[][] arr, int N, int M) {
        for(int sr=1; sr<=N; sr++) {
            for(int sc=1; sc<=M; sc++) {
                for(int er=sr; er<=N; er++) {
                    for(int ec=sc; ec<=M; ec++) {
                        long sum = arr[er][ec] - arr[er][sc-1] - arr[sr-1][ec]
                                + arr[sr-1][sc-1];

                        MAX = Math.max(MAX, sum);
                    }
                }
            }
        }
    }
}