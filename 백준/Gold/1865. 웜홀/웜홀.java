import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, W, INF = 10001;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (TC > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(map[i], INF);
            }

            int S, E, T;

            // 도로 정보 (중복 가능)
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()) - 1;
                E = Integer.parseInt(st.nextToken()) - 1;
                T = Integer.parseInt(st.nextToken());
                map[S][E] = map[E][S] = Math.min(map[S][E], T);
            }

            // 웜홀 정보
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()) - 1;
                E = Integer.parseInt(st.nextToken()) - 1;
                T = Integer.parseInt(st.nextToken());
                map[S][E] = Math.min(map[S][E], -1 * T);
            }

            // 플로이드 워샬
            for(int step=0; step<N; step++) {
                for(int start=0; start<N; start++) {
                    if(map[start][step] == INF) continue;

                    for(int end=0; end<N; end++) {
                        if(map[step][end] == INF) continue;

                        if(map[start][end] > map[start][step] + map[step][end]) {
                            map[start][end] = map[start][step] + map[step][end];
                        }
                    }
                }
            }

            boolean isPossible = false;
            for(int i=0; i<N; i++){
                if(map[i][i] < 0) {
                    isPossible = true;
                    break;
                }
            }

            sb.append(isPossible? "YES": "NO").append("\n");
            TC--;
        }

        System.out.print(sb);
    }

}