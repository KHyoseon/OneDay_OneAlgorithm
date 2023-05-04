import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int[M];
        dfs(1, 0);

        System.out.print(sb);
    }

    private static void dfs(int moreThan, int cur) {
        if(cur == M) {
            for(int l: list)
                sb.append(l).append(" ");
            sb.append("\n");
            return;
        }
        for(int d=moreThan; d<=N; d++) {
            list[cur] = d;
            dfs(d, cur+1);
        }
    }
}