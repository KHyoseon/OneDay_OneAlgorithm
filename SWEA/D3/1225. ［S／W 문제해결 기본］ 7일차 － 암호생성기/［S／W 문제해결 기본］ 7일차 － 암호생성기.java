import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[8];

        StringTokenizer st;
        for(int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#").append(test_case).append(" ");
            br.readLine();
            st = new StringTokenizer(br.readLine());

            int MIN = Integer.MAX_VALUE;
            for(int i=0; i<8; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                MIN = Math.min(MIN, arr[i]);
            }

            int temp = (MIN / 15) - 1;
            temp *= 15;
            for(int i=0; i<8; i++) {
                arr[i] -= temp;
            }

            int i=0, j;
            for(i=0, j=0; ; i++, j++) {
                arr[i%8] -= (j%5)+1;
                if(arr[i%8] <= 0) break;
            }

            for(int s=1; s<8; s++) {
                sb.append(arr[(i + s)%8]).append(" ");
            }

            sb.append("0\n");
        }

        System.out.println(sb);
    }
}