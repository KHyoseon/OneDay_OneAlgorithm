import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int K;
    static char[] sign;
    static String MAX = "0", MIN = "9999999999";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());

        String input = br.readLine().replace(" ", "");
        sign = input.toCharArray();

        start();

        sb.append(MAX).append("\n").append(MIN);
        System.out.println(sb);
    }

    private static void start() {
        char[] selected = new char[K+1];
        for(int i=0; i<10; i++) {
            selected[0] = (char) (i + '0');
            comb(1, selected, 1<<i);
        }
    }

    private static void comb(int index, char[] selected, int flag) {
        if(index == K+1) {
            String number = String.valueOf(selected);
            if(MAX.compareTo(number) < 0) {
                MAX = number;
            }
            if(MIN.compareTo(number) > 0) {
                MIN = number;
            }
            return;
        }

        int[] range = new int[2];
        if(sign[index-1] == '<') {
            // selected[index-1] 보다 큰 값만 넣어야 함
            range[0] = selected[index - 1] - '0' + 1;
            range[1] = 10;
        } else {
            // selected[index-1] 보다 작은 값만 넣어야 함
            range[1] = selected[index - 1] - '0';
        }

        for(int i=range[0]; i<range[1]; i++) {
            if((flag & 1<<i) != 0) continue;
            selected[index] = (char) (i + '0');
            comb(index+1, selected, flag|(1<<i));
        }
    }
}