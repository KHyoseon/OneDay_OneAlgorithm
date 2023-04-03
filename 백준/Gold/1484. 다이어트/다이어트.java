import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        int a = (int)Math.sqrt(G) + 1, b = 1;
        double a2, b2;

        while(b < a){
            a2 = Math.pow(a, 2);
            b2 = Math.pow(b, 2);
            if(a2 - b2 == G) {
                sb.append(a).append("\n");
                a++; b++;
            } else if(a2 - b2 < G) {
                a++;
            } else {
                b++;
            }
        }

        if(sb.length()==0){
            sb.append("-1");
        }

        System.out.println(sb);
    }

}