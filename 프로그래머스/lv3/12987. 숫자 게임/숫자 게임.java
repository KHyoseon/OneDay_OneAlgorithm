import java.util.*;
class Solution {
    Deque<Integer> dq = new ArrayDeque<>();

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        for(int i=n-1; i>=0; i--) {
            dq.add(B[i]);
        }

        int answer = 0;
        int target, i=n-1;

        while(i>=0 && !dq.isEmpty()) {
            target = A[i--];
            if(dq.peekFirst() > target) {
                answer++;
                dq.pollFirst();
            } else if(dq.peekFirst() < target) {
                dq.pollLast();
            }
        }

        return answer;
    }
}