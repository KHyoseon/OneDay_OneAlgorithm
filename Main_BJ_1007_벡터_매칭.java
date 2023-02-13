package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_BJ_1007_벡터_매칭 {
    static int N;
    static int[][] coordi;
    static boolean[] selected;
    static double MIN;
    static Set<Integer> sets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T > 0) {
            N = Integer.parseInt(br.readLine());
            coordi = new int[N][2];
            sets = new HashSet<>();
            MIN = Double.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                coordi[i][0] = Integer.parseInt(input[0]);
                coordi[i][1] = Integer.parseInt(input[1]);
            }
            comb(0, 0, 0);
            sb.append(MIN).append("\n");
            T--;
        }

        System.out.println(sb);
    }

    private static void comb(int start, int cnt, int flag){
        if(cnt == N/2) {
            // 짝 최소 값 찾기

            if(sets.contains((int)(Math.pow(2, N) - 1) - flag)) return;
            sets.add(flag);

            selected = new boolean[N];
            match(flag, 0, 0);
            return;
        }

        for(int i=start; i<N; i++){
            comb(i + 1, cnt + 1, flag | (1 << i));
        }
    }

    private static void match(int flag, int now, double sum) {
        if(now == N/2) {
            MIN = Math.min(MIN, sum);
            return;
        }

        if(sum >= MIN) return;

        for(int s=now; s<N; s++) {
            if((flag & (1<<s)) == 0) continue;

            for(int ss=0; ss<N; ss++) {
                if ((flag & (1<<ss)) != 0) continue;
                if (selected[ss]) continue;

                selected[ss] = true;
                match(flag, s + 1, sum + dist(s, ss));
                selected[ss] = false;
            }
        }
    }

    private static double dist(int i, int j) {
        return Math.sqrt(Math.pow(coordi[i][0] - coordi[j][0], 2) + Math.pow(coordi[i][1] - coordi[j][1], 2));
    }
}
