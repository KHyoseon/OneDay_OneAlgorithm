import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for(int i=0; i<N; i++)
            nums[i] = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        int gcd = nums[1]-nums[0];
        for (int i = 2; i < N; i++) {
            gcd = GCD(gcd, nums[i]-nums[i-1]);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue pq = new PriorityQueue();
        for(int i=2; i<=Math.sqrt(gcd); i++){
            if(i*i == gcd)
                pq.add(i);
            else if(gcd%i == 0) {
                pq.add(i);
                pq.add(gcd / i);
            }
        }
        pq.add(gcd);

        while (!pq.isEmpty())
            sb.append(pq.poll()).append(' ');

        System.out.println(sb);
    }

    static int GCD(int a, int b){
        int tmp;
        while(b!=0){      //b가 0이 될 때까지
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}