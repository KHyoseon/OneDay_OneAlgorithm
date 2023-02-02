import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        if (s == 1 || n > s) return new int[]{-1};

        int nums[] = new int[n];
        int remain = s;

        for(int i=0; i<n-1; i++){
            nums[i] = remain / (n-i);
            remain -= nums[i];
        }
        nums[n-1] = remain;

        return nums;
    }
}