package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_거듭_제곱 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N, M;
        int result = 1;

        StringTokenizer st;
        for(int test_case = 1; test_case <= 10; test_case++) {
            br.readLine();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            result = func(N, M);

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int func(int n, int m) {
        if(m == 1) return n;
        if(m == 2) return n*n;

        int ret = func(n, m / 2);

        if(m % 2 == 0)        return ret * ret;
        return ret * ret * n;
    }
}