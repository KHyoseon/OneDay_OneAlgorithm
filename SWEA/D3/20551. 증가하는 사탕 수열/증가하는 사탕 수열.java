import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        int t=0;
        while(t < TC) {
            sb.append("#"+(++t)+" ");

            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            int C = Integer.parseInt(input[2]);

            int sum = 0;

            if(C<3 || B<2) sum = -1;
            else {
                if (B >= C - 1) {
                    sum += B - C + 1;
                    B = C - 1;
                }
                if (A >= B - 1) {
                    sum += A - B + 1;
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}