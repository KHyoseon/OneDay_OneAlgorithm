import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2629_양팔저울 {
    static int N, M;
    static int[] weights, check;
    static boolean[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        memo = new boolean[N+1][40001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        weights = new int[N+1];
        int i = 0;
        while(st.hasMoreTokens()){
            weights[i++] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        check = new int[M];
        i = 0;
        while(st.hasMoreTokens()){
            check[i++] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        String yes = "Y ", no = "N ";
        for(int c: check){
            if(memo[N][c]) sb.append(yes);
            else sb.append(no);
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    private static void solve(int i, int result) {
        if(i > N || memo[i][result])     return;

        memo[i][result] = true;
        solve(i+1, result);
        solve(i+1, result + weights[i]);
        solve(i+1, Math.abs(result - weights[i]));
    }
}