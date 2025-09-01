import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int[] tree = new int[1 << (h + 1)];

        init(arr, tree, 1, 0, N-1);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if(a==1) { // b번째 값 c로 변경
                int diff = c - arr[b];
                // 나중에 arr[b]를 또 변경할 수 있으므로 갱신해 줌
                arr[b] = c;
                // tree, node번호, node가 아우르는 범위 s-e, index, diff
                update(tree, 1, 0, N-1, b, diff);
            } else { // [b, c] 구간에서 가장 작은 값 출력
                // tree, node번호, node가 아우르는 범위 s-e, 출력할 범위 b-c
                sb.append(getMin(tree, 1, 0, N-1, b, c-1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void init(int[] arr, int[] tree, int node, int start, int end) {
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
        tree[node] = Math.min(tree[2*node], tree[2*node + 1]);
    }

    public static int update(int[] tree, int node, int start, int end, int index, long diff) {
        // start-end 범위 밖이면 종료
        if(index<start || end<index) return tree[node];
        // 리프 노드면 여기서 종료
        if(start == end) {
            tree[node] += diff;
            return tree[node];
        }
        // 리프 노드 만날 때까지 반복
        int ret1 = update(tree, 2*node, start, (start+end)/2, index, diff);
        int ret2 = update(tree, 2*node + 1, (start+end)/2 + 1, end, index, diff);
        return tree[node] = Math.min(ret1, ret2);
    }

    public static int getMin(int[] tree, int node, int start, int end, int left, int right) {
        // left-right가 아예 start-end 범위 밖이면 종료
        if(end<left || right<start) return Integer.MAX_VALUE;
        // start-end가 left-right 범위 내에 완전 포함이면 start-end 구간 값 다 리턴
        if(left<=start && end<=right) return tree[node];
        // left-right가 start-end 범위에 걸쳐 있으면
        int ret1 = getMin(tree, 2*node, start, (start+end)/2, left, right);
        int ret2 = getMin(tree, 2*node + 1, (start+end)/2 + 1, end, left, right);
        return Math.min(ret1, ret2);
    }
}