import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<3; tc++){
            int N = Integer.parseInt(br.readLine());
            BigInteger sum = new BigInteger("0");

            for(int i=0; i<N; i++) {
                sum = sum.add(new BigInteger(br.readLine()));
            }

            if(sum.signum() == 0)
                sb.append("0\n");
            else if(sum.signum() == -1)
                sb.append("-\n");
            else
                sb.append("+\n");
        }

        System.out.println(sb);
    }
}