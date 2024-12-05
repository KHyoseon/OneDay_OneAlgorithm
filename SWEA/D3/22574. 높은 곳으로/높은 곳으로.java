import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        while(TC > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int P = Integer.parseInt(input[1]);

            int floor = 0;
            for(int i=1; i<=N; i++) {
                floor += i;
                if(floor == P) floor--;
            }

            sb.append(floor).append("\n");
            TC--;
        }

        System.out.println(sb);
    }
}