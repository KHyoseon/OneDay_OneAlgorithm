package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1238_파티 {
    static int N, M, X, INF = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(map[i], INF);
        }

        int v1, v2, time;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            time = Integer.parseInt(st.nextToken());

            map[v1][v2] = time;
        }

        // X번 마을 -> 다른 마을
        for(int step=0; step<N; step++) {
            if(step==X) continue;
            for(int dest=0; dest<N; dest++) {
                if(step==dest || dest==X) continue;
                map[X][dest] = Math.min(map[X][dest], map[X][step] + map[step][dest]);
            }
        }

        // 다른 마을 -> X번 마을
        for(int start=0; start<N; start++) {
            if(start==X) continue;
            for(int step=0; step<N; step++) {
                if(start==step || step==X) continue;
                map[start][X] = Math.min(map[start][X], map[start][step] + map[step][X]);
            }
        }

        int answer = 0;

        for(int i=0; i<N; i++) {
            if(i==X) continue;
            if(answer < map[i][X] + map[X][i]) {
                answer = map[i][X] + map[X][i];
            }
        }

        System.out.println(answer);
    }
}