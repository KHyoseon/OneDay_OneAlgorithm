import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int cnt = 1;
        while(B>0 && A!=B) {
            if(B%10 == 1) {
                B/=10;
            } else if(B%2 == 1) {
                cnt = -1;
                break;
            } else {
                B/=2;
            }
            cnt++;
        }

        if(B<=0) cnt = -1;

        System.out.println(cnt);
    }
}