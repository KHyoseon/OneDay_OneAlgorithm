import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0, l=A.length; i<l; i++) {
            answer += (A[i]*B[l-i-1]);
        }

        return answer;
    }
}