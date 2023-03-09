import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int change = Integer.parseInt(br.readLine());
        int five = change / 5;
        int rest = change - 5*five;

        if(change < 5) {
            if(change%2 == 0) {
                System.out.println(change/2);
            } else {
                System.out.println("-1");
            }
        }
        else {
            while (five >= 0) {
                if (rest % 2 == 0) {
                    System.out.println(five + rest / 2);
                    return;
                }
                five--;
                rest += 5;
            }
            System.out.println("-1");
        }
    }
}
