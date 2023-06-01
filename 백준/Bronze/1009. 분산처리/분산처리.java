import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, Integer> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        int a, b;
        String[] input;
        while(TC > 0) {
            dp = new HashMap<>();
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]) % 10;
            b = Integer.parseInt(input[1]);

            dp.put(0, 1);
            dp.put(1, a);

            int ret = myPow(a, b);
            sb.append(ret==0? "10": ret).append("\n");
            TC--;
        }

        System.out.print(sb);
    }

    private static int myPow(int a, int b) {
        if(!dp.containsKey(b)) {
            int half = myPow(a, b / 2);
            int answer = half * half;
            if(b%2 == 1) answer *= a;
            dp.put(b, answer % 10);
        }
        return dp.get(b);
    }

}