import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9095 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] memo = new int[20];
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;

        for(int cur=4; cur<15; cur++) {
            memo[cur] = memo[cur-1] + memo[cur-2] + memo[cur-3];
        }

        for(int i=0; i<T; i++){
            int target = Integer.parseInt(br.readLine());
            System.out.println(memo[target]);
        }
    }
}
