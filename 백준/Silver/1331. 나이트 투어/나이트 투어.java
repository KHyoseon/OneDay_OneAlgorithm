import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = new String[37];
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 36; i++) {
            input[i] = br.readLine();
            if(set.contains(input[i])){
                System.out.println("Invalid");
                return;
            }
            set.add(input[i]);
        }

        input[36] = input[0];
        char pa = input[0].charAt(0);
        char pn = input[0].charAt(1);

        for(int i=1; i<37; i++) {
            int a = Math.abs(input[i].charAt(0) - pa);
            int n = Math.abs(input[i].charAt(1) - pn);

            if(0==a || 0==n) {
                System.out.println("Invalid");
                return;
            }

            if(a + n == 3) {
                pa = input[i].charAt(0);
                pn = input[i].charAt(1);
                continue;
            }

            System.out.println("Invalid");
            return;
        }

        System.out.println("Valid");
    }

}