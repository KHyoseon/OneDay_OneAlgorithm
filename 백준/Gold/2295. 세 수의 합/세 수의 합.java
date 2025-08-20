import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];

        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(input);

        int[] cache = new int[N*N];
        int c=0;
        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++) {
                cache[c++] = input[i]+input[j];
            }
        }
        Arrays.sort(cache);

        for(int i=input.length-1; 0<i; i--) {
            for(int j=i-1; 0<=j; j--) {
                int need = input[i] - input[j];
                // need가 존재 하면
                if(binarySearch(need, cache)) {
                    System.out.println(input[i]);
                    return;
                }
            }
        }
    }

    private static boolean binarySearch(int need, int[] cache) {
        int l=0, r= cache.length-1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(cache[mid] == need)
                return true;
            else if(cache[mid] < need)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return false;
    }
}