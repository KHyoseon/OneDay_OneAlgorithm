import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] number = br.readLine().toCharArray();
        int[] num = new int[number.length];

        int i=0;
        for(char n: number)
            num[i++] = (n-48);

        Arrays.sort(num);

        for(int l=num.length-1; l>=0; l--)
            sb.append(num[l]);

        System.out.println(sb);
    }
}
