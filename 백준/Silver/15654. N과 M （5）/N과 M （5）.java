import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        list = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        dfs(0, 0, new int[M], 0);
        System.out.print(sb);
    }

    private static void dfs(int cur, int index, int[] arr, int flag) {
        if(cur == M) {
            for(int l: arr)
                sb.append(l).append(" ");
            sb.append("\n");
            return;
        }
        for(int d=0; d<N; d++) {
            if((flag&(1<<d)) != 0) continue;
            arr[index] = list[d];
            dfs(cur+1, index+1, arr, flag|(1<<d));
        }
    }
}