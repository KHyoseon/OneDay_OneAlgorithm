import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<int[]>[] list;
    static boolean[] visited;
    static int N, MAX=0, far;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        // list 구성
        StringTokenizer st;
        int v1, v2, dist;
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            list[v1].add(new int[]{v2, dist});
            list[v2].add(new int[]{v1, dist});
        }

        visited = new boolean[N];
        dfs(0, 0);

        visited = new boolean[N];
        dfs(far, 0);

        System.out.println(MAX);
    }

    private static void dfs(int n, int dist) {
        if(MAX < dist){
            MAX = dist;
            far = n;
        }

        visited[n] = true;

        int[] cur;
        for(int i = 0, l=list[n].size(); i < l; i++) {
            cur = list[n].get(i);
            if(!visited[cur[0]]) {
                dfs(cur[0], dist + cur[1]);
                visited[cur[0]] = true;
            }
        }
    }

}