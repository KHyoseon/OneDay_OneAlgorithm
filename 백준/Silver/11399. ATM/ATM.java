import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N];
        int[] sum = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int k=0; st.hasMoreTokens(); k++){
            time[k] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);
        for(int i=0; i<N; i++){
            sum[i] = time[i];
        }

        for(int i=0; i<N-1; i++){
            sum[i+1] += sum[i];
        }

        int answer=0;
        for(int s=0; s<N; s++)
            answer += sum[s];
        System.out.println(answer);
    }
}