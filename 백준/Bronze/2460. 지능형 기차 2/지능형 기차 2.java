import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int sum = 0, MAX = 0;
        StringTokenizer st;
        for(int i=0; i<10; i++) {
            st = new StringTokenizer(br.readLine());
            sum -= Integer.parseInt(st.nextToken());
            sum += Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, sum);
        }
        System.out.println(MAX);
    }
}