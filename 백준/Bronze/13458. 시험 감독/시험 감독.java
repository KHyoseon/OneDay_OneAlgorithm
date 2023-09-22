import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int rooms[] = new int[N];

        int i=0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            rooms[i++] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long SUM = 0;
        for(int n=0; n<N; n++) {
            int sum = 1;
            rooms[n] = Math.max(rooms[n] - B, 0);
            sum += rooms[n] / C;
            sum += (rooms[n] % C)==0? 0: 1;
            SUM += sum;
        }

        System.out.println(SUM);
    }

}
