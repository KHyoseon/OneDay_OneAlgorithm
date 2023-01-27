import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int X = Integer.parseInt(input[0]);
        int Y = Integer.parseInt(input[1]);

        if(X == Y || rate(Y, X)==99){
            System.out.println("-1");
            return;
        }

        System.out.println(binarySearch(X, Y));
    }

    private static long rate(long y, long x) {
        return (y * 100) / x;
    }

    // k판 더 하면 바뀌어 있을까? -> 참/거짓
    // 최솟값을 찾아야 하니까 f(low)==f(mid)인 high를 찾아야 함.
    private static long binarySearch(int X, int Y){
        long low = 0;
        long high = X;

        while (rate(Y+low, X+low)==rate(Y+high, X+high)){
            low = high;
            high *= X;
        }

        while(low+1<high){
            long mid = (low+high)/2;
            if(rate(Y+low, X+low)==rate(Y+mid, X+mid)){
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }
}