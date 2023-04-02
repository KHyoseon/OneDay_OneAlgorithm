import java.util.Arrays;
public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int N = land.length * land.length;
        long[] prev = new long[2];

        long[] blocks = new long[N];
        for(int i=0, n=land.length; i<N; i++) {
            blocks[i] = land[i / n][i % n];
        }

        Arrays.sort(blocks);

        long sum = 0;
        for(int i=0; i<N; i++) {
            sum += blocks[i];
        }

        prev[1] = sum - (blocks[0] * N);
        long MIN = prev[1] * Q;

        for (int i = 1; i < N; i++) {
            if (blocks[i] == blocks[i - 1]) continue;
            prev[0] += (blocks[i] - blocks[i - 1]) * i;
            prev[1] -= (N - i) * (blocks[i] - blocks[i - 1]);
            if (MIN < prev[0] * P + prev[1] * Q) break;
            MIN = prev[0] * P + prev[1] * Q;
        }

        return MIN;
    }
}