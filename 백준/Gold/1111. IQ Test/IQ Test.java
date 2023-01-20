import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 1){
            System.out.println("A");
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int i=0;
        int[] arr = new int[N];
        while(st.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(st.nextToken());
        }

        if(N >= 3) {
            int a;
            if(arr[1]==arr[0]) {
                a = 0;
            } else if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
                System.out.println("B");
                return;
            } else {
                a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            }
            int b = arr[2] - (arr[1] * a);
            System.out.println(ok(arr, N, a, b));
        } else if(arr[0]==arr[1] && N==2) {
            System.out.println(arr[0]);
        } else {
            System.out.println("A");
        }
    }

    private static String ok(int[] arr, int N, int a, int b){
        for(int j=N-1; j>0; j--) {
            if (arr[j] != a * arr[j-1] + b) return "B";
        }
        return (a * arr[N - 1] + b) + "";
    }

}