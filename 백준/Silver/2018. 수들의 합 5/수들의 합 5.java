import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int p1 = 1, p2 = 2;
        int sum = p1;

        int answer = 1;

        while(p1<N) {
            if(sum == N) {
                answer++;
                sum -= p1;
                p1++;
            }
            sum += p2;
            if(sum > N) {
                while (sum > N) {
                    sum -= p1;
                    p1++;
                }
            }
            p2++;
        }

        System.out.println(answer);
    }

}