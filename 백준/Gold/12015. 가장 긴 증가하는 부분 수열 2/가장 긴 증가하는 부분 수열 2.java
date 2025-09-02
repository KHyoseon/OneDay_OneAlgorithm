import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[N];

        int size = 0;
        for(int cur: arr) {
            int l = 0, r = size;
            while (l < r) {
                int m = (l + r) / 2;
                if (lis[m] >= cur) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            lis[l] = cur;
            if(l == size)
                size++;
        }

        System.out.println(size);
    }
}