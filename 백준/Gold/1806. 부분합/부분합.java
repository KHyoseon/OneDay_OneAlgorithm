import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int first = 0, last = 1, SUM = Integer.MAX_VALUE;
        int sum = numbers[first];

        while(last<=N) {
            if(sum >= S) {
                // 앞에서부터 길이 줄이기
                while (sum-numbers[first] >= S && first<last-1) {
                    sum -= numbers[first];
                    first++;
                }
                // 갱신
                SUM = Math.min(SUM, last-first);
                // 다시 시작
                sum -= numbers[first];
                first++;
                sum += numbers[last];
            } else {
                sum += numbers[last];
            }
            last++;
        }

        System.out.println(SUM == Integer.MAX_VALUE? 0: SUM);
    }

}