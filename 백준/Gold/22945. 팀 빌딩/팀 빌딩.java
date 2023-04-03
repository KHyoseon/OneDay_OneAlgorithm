import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] workers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            workers[i] = Integer.parseInt(st.nextToken());
        }

        int s=0, e=N-1;
        int MAX = 0, temp = 0;

        while (s<e && e<N) {
            temp = (e-s-1);
            if(workers[s] < workers[e]) {
                temp *= workers[s];
                s++;
            } else if(workers[s] > workers[e]) {
                temp *= workers[e];
                e--;
            } else {
                temp *= workers[s];
                if(workers[s+1] < workers[e-1]) s++;
                else e--;
            }
            MAX = Math.max(MAX, temp);
        }

        System.out.println(MAX);
    }

}