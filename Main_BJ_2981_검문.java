import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_BJ_2981_검문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for(int i=0; i<N; i++)
            nums[i] = Integer.parseInt(br.readLine());

        // 정렬
        Arrays.sort(nums);

        // 이웃한 수의 차이끼리의 최대공약수(GCD)를 구한다
        int gcd = nums[1]-nums[0];
        for (int i = 2; i < N; i++) {
            gcd = GCD(gcd, nums[i]-nums[i-1]);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue pq = new PriorityQueue();

        // 구한 GCD의 약수를 pq에 넣는다
        for(int i=2; i<=Math.sqrt(gcd); i++){
            // 제곱근은 따로 넣음
            if(i*i == gcd)
                pq.add(i);
            else if(gcd%i == 0) {
                pq.add(i);
                pq.add(gcd / i);
            }
        }
        // GCD도 넣음
        pq.add(gcd);

        while (!pq.isEmpty())
            sb.append(pq.poll()).append(' ');

        System.out.println(sb);
    }

    // a: 나뉠 수(B), b: 나눌 수(A%B)
    static int GCD(int a, int b){
        int tmp;
        while(b!=0){
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
