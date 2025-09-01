import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        long[] tree = new long[N*4+1];

        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(arr, tree, 1, 0, N-1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;

            if(a==1) { // b번째 값 c로 변경
                long c = Long.parseLong(st.nextToken());
                long diff = c - arr[b];
                arr[b] = c;
                // tree, node번호, node가 아우르는 범위 s-e, index, diff
                update(tree, 1, 0, N-1, b, diff);
            } else { // [b, c] 구간 합 출력
                int c = Integer.parseInt(st.nextToken()) - 1;
                // tree, node번호, node가 아우르는 범위 s-e, 출력할 범위 b-c
                sb.append(sum(tree, 1, 0, N-1, b, c)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void init(long[] arr, long[] tree, int node, int start, int end) {
        // leaf 노드일 경우
        if(start == end) {
            tree[node] = arr[start];
            return;
        }
        // left-sub-tree 구성
        init(arr, tree, 2*node, start, (start+end)/2);
        // right-sub-tree 구성
        init(arr, tree, 2*node + 1, (start+end)/2 + 1, end);
        // 현재 node에 좌/우 부분 합 더해서 저장
        tree[node] = tree[2*node] + tree[2*node + 1];
    }

    public static void update(long[] tree, int node, int start, int end, int index, long diff) {
        // start-end 범위 밖이면 종료
        if(index<start || end<index) return;
        // 해당 범위면 diff 더해줌
        tree[node] += diff;
        // 리프 노드면 여기서 종료
        if(start == end) return;
        // 리프 노드 만날 때까지 반복
        update(tree, 2*node, start, (start+end)/2, index, diff);
        update(tree, 2*node + 1, (start+end)/2 + 1, end, index, diff);
    }

    public static long sum(long[] tree, int node, int start, int end, int left, int right) {
        // left-right가 아예 start-end 범위 밖이면 종료
        if(end<left || right<start) return 0;
        // start-end가 left-right 범위 내에 완전 포함이면 start-end 구간 값 다 리턴
        if(left<=start && end<=right) return tree[node];
        // left-right가 start-end 범위에 걸쳐 있으면
        long s1 = sum(tree, 2*node, start, (start+end)/2, left, right);
        long s2 = sum(tree, 2*node + 1, (start+end)/2 + 1, end, left, right);
        return s1+s2;
    }
}