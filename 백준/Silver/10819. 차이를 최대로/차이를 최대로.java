import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; st.hasMoreTokens(); i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        comb(arr, 0, new int[N], new boolean[N]);

        System.out.print(MAX);
    }

    private static void comb(int[] arr, int cnt, int[] result, boolean[] selected) {
        if(cnt == arr.length) {
            MAX = Math.max(MAX, calc(result));
            return;
        }

        for(int i=0, l=arr.length; i<l; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            result[cnt] = arr[i];
            comb(arr, cnt + 1, result, selected);
            selected[i] = false;
        }
    }

    private static int calc(int[] result) {
        int sum = 0;
        for(int i=0, l=result.length; i<l-1; i++) {
            sum += (Math.abs(result[i] - result[i + 1]));
        }
        return sum;
    }

}