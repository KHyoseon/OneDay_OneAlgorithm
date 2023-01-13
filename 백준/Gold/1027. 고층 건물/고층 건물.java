import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int i=0;
        while(st.hasMoreTokens()){
            buildings[i++] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[N];

        for(int now=0; now<N; now++){
            float[] watch = new float[N];
            float maxL = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;

            // left
            for(int left=now-1; left>=0; left--){
                watch[left] = (float) (buildings[left] - buildings[now]) / Math.abs(now-left);
                if (watch[left] > maxL) {
                    cnt[now]++;
                    maxL = watch[left];
                }
            }
            // right
            for(int right=now+1; right<N; right++){
                watch[right] = (float) (buildings[right] - buildings[now]) / Math.abs(now-right);
                if (watch[right] > maxR) {
                    cnt[now]++;
                    maxR = watch[right];
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int canSee: cnt)
            pq.add(canSee);

        System.out.println(pq.peek());
    }
}