import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int P = Integer.parseInt(br.readLine());
        int arr[] = new int[20];

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(P > 0) {
            int SUM = 0;

            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());

            for(int i=0; i<20; i++) {
                int cnt = 0;
                int ME = arr[i] = Integer.parseInt(st.nextToken());

                int tail = i;
                while(tail - 1 >= 0 && arr[tail-1] > ME) {
                    arr[tail] = arr[tail-1];
                    cnt++;
                    tail--;
                }
                tail = Math.max(tail, 0);
                arr[tail] = ME;
                SUM += cnt;
            }

            sb.append(T).append(" ").append(SUM).append("\n");
            P--;
        }

        System.out.print(sb);
    }

}