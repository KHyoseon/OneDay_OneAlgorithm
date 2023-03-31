import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, sum;
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numbers);

        for(int i=0; i<N; i++) {
            findGoodNumber(i, 0 , N-1);
        }

        System.out.println(sum);
    }

    private static void findGoodNumber(int target, int l, int r) {
        if(l >= r) return;
        if(numbers[l] + numbers[r] < numbers[target] || l==target)
            findGoodNumber(target, l+1, r);
        else if(numbers[l] + numbers[r] > numbers[target] || r==target)
            findGoodNumber(target, l, r-1);
        else sum++;
    }

}