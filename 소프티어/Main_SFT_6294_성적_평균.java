import java.io.*;
import java.util.*;

public class Main_SFT_6294_성적_평균 {

    public static int N, K;
    public static int[] scores;
    public static ArrayList<int[]> ranges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            ranges.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        StringBuilder sb = new StringBuilder();
        
        for(int[] rng: ranges) {
            float sum = 0;
            for(int i=rng[0]-1; i<rng[1]; i++)
                sum += scores[i];
            sb.append(String.format("%.2f", sum/(rng[1]-rng[0]+1))).append("\n");
        }

        System.out.print(sb);
    }
}
