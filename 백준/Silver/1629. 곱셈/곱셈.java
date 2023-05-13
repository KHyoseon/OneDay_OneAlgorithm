import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static long A, B, C;
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        A = Long.parseLong(input[0]);
        B = Long.parseLong(input[1]);
        C = Long.parseLong(input[2]);

        A %= C;
        map.put(0L, 1L);
        map.put(1L, A);
        map.put(2L, (A * A) % C);

        System.out.println(myPow(B));
    }

    private static long myPow(long time) {
        if(map.containsKey(time)) return map.get(time);

        long half = myPow(time / 2);
        if(time%2 ==0) {
            map.put(time, (half * half) % C);
        } else {
            map.put(time, (((half * half) % C) * A) % C);
        }
        return map.get(time);
    }
}