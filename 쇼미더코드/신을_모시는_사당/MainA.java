import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String input = br.readLine().replace(" ", "");

        int[] one = new int[N];
        int[] two = new int[N];

        int i=0;
        for(char c: input.toCharArray()){
            if(c=='2')  one[i] = -1;
            else one[i] = 1;
            i++;
        }

        int[] ret = sum(one);

        System.out.println(Math.max(ret[0], Math.abs(ret[1])));
    }

    static int[] sum(int list[]){
        int N = list.length, MIN = Integer.MIN_VALUE, MAX = Integer.MAX_VALUE, ppsum = 0, pmsum = 0;
        int i;
        for(i=0; i<N; ++i){
            ppsum = Math.max(ppsum, 0) + list[i];
            MIN = Math.max(ppsum, MIN);

            pmsum = Math.min(pmsum, 0) + list[i];
            MAX = Math.min(pmsum, MAX);
        }
        return new int[]{MIN, MAX};
    }

}
