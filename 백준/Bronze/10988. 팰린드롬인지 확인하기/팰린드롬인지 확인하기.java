import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = input.length();
        int answer = 1;
        for(int l=0, r=N-1; l<r; l++, r--) {
            if(input.charAt(l) != input.charAt(r)) {
                answer = 0;
                break;
            }
        }
        System.out.println(answer);
    }
}