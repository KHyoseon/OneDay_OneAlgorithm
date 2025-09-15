import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] works = new int[N][2];

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            int size = Integer.parseInt(input[0]);
            int deadLine = Integer.parseInt(input[1]);
            works[i][0] = size;
            works[i][1] = deadLine;
        }

        Arrays.sort(works, Comparator.comparingInt(o -> o[1]));

        int start = works[N-1][1] - works[N-1][0];
        for(int i=N-2; 0<=i; i--) {
            start = Math.min(start, works[i][1]) - works[i][0];
        }

        System.out.println(start);
    }
}