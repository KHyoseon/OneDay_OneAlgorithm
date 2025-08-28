import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A;
    static int[] nums, memo, parent;
    static int cache[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = Integer.parseInt(br.readLine());
        nums = new int[A+1];
        memo = new int[A+1];
        parent = new int[A+1];

        int idx = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            nums[idx] = Integer.parseInt(st.nextToken());
            parent[idx] = idx;
            idx++;
        }

        nums[0] = Integer.MIN_VALUE;

        int cnt = lis(0);
        System.out.println(cnt);

        StringBuilder sb = new StringBuilder();
        int cur=0;
        while(cur != parent[cur]) {
            cur = parent[cur];
            sb.append(nums[cur]).append(" ");
        }
        System.out.println(sb);
    }

    private static int lis(int start) {
        if (start > A) return 0;
        if (parent[start] != start) return memo[start];

        int max = 0, p = start;
        for(int next = start+1; next<=A; next++) {
            if(nums[start] < nums[next]) {
                int ret = lis(next);
                if(max < ret+1) {
                    p = next;
                    max = ret+1;
                }
            }
        }

        parent[start] = p;
        return memo[start] = max;
    }
}