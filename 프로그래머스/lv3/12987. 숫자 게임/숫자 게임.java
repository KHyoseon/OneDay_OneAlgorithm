import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        int answer = 0;
        for(int a=n-1, b=a; a>=0 && b>=0; a--) {
            if(A[a] < B[b]) {
                answer++;
                b--;
            }
        }

        return answer;
    }
}