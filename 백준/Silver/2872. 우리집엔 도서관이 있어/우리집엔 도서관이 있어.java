import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] bucket = new int[N];

        for(int i=0; i<N; i++) {
            bucket[i] = Integer.parseInt(br.readLine());
        }

        int peek;
        int max=N, cnt=0;
        for(int i=N-1; i>=0; i--) {
            if(bucket[i] == max){
                cnt++;
                max--;
            }
        }

        System.out.println(N-cnt);
    }
}