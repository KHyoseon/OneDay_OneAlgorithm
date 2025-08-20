import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 돌 게임 NM
        int T = Integer.parseInt(br.readLine());
        while(0 < T) {
            T--;
            String input[] = br.readLine().split(" ");
            long N[] = new long[2];
            N[0] = Long.parseLong(input[0]);
            N[1] = Long.parseLong(input[1]);

            if (N[0]==1 || N[1]==1 || (N[0]+N[1])%2 == 1)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}