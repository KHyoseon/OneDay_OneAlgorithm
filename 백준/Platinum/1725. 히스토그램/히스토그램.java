import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] heights, tree;
    static int N;
    static long MAX = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        heights = new int[N+1];
        heights[0] = Integer.MAX_VALUE;

        for(int i=1; i<=N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new int[1<<(h+1)];
        init(1, 1, N);

        getMaxExtent(1, N);

        System.out.println(MAX);
    }

    private static void getMaxExtent(int start, int end) {
        if(start == end) {
            MAX = Math.max(MAX, heights[start]);
            return;
        }

        // 1. 구간 내에서 최솟값과 그 index를 찾는다.
        int index = query(1, 1, N, start, end);

        // 2. (최솟값 * 구간 길이)로 사각형 넓이 갱신한다.
        long extent = heights[index] * (end - start + 1);
        MAX = Math.max(MAX, extent);

        // 3. index 기준으로 왼쪽, 오른쪽 사각형 넓이를 구한다.
        if(start <= index-1)
            getMaxExtent(start, index - 1);
        if(index+1 <= end)
            getMaxExtent(index + 1, end);
    }

    private static int query(int node, int start, int end, int left, int right) {
        if(end < left || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int ret1 = query(2 * node, start, (start + end) / 2, left, right);
        int ret2 = query(2 * node + 1, (start + end) / 2 + 1, end, left, right);

        if(heights[ret1] <= heights[ret2]) return ret1;
        else return ret2;
    }

    private static void init(int node, int start, int end) {
        if(start == end) {
            tree[node] = start;
            return;
        }

        init(2 * node, start, (start + end) / 2);
        init(2 * node + 1, (start + end) / 2 + 1, end);

        if(heights[tree[2 * node]] <= heights[tree[2 * node + 1]])
            tree[node] = tree[2 * node];
        else tree[node] = tree[2 * node + 1];
    }
}