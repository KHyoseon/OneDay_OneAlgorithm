package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_2012_등수_매기기 {
    static int N;
    static int[] expected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        expected = new int[N+1];

        for (int i = 1; i <= N; i++) {
            expected[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(expected);

        long sum = 0;
        for(int sort=1; sort<=N; sort++) {
            sum += Math.abs(sort - expected[sort]);
        }

        System.out.println(sum);
    }
}
