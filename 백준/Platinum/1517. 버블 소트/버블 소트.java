import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // [0]: index(1~N), [1]: element
        int[][] arr = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i][0] = i+1;
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] ordered = new int[N][2];
        for(int i=0; i<N; i++) {
            ordered[i] = arr[i].clone();
        }

        // element 순으로 정렬
        Arrays.sort(ordered, Comparator.comparingInt(o -> o[1]));

        // segment tree
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int[] tree = new int[1<<(h + 1)];

        long count = 0;
        for(int[] num: ordered) {
            // 방명록 확인
            count += query(tree, 1, 1, N, Math.min(num[0]+1, N), N);
            // 방명록 작성
            update(tree, 1, 1, N, num[0]);
        }

        System.out.println(count);
    }

    private static void update(int[] tree, int node, int start, int end, int index) {
        if(index < start || end < index) return;
        tree[node] += 1;
        if(start == end) return;
        update(tree, 2*node, start, (start+end) / 2, index);
        update(tree, 2*node + 1, (start+end) / 2 + 1, end, index);
    }

    private static long query(int[] tree, int node, int start, int end, int left, int right) {
        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];
        long lSum = query(tree, 2*node, start, (start+end) / 2, left, right);
        long rSum = query(tree, 2*node + 1, (start+end) / 2 + 1, end, left, right);
        return lSum + rSum;
    }

}