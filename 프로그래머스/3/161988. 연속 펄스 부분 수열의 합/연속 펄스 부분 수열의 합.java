import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int N = sequence.length;
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        
        int purse = 1;
        for(int i=0; i<N; i++) {
            arr1[i] = sequence[i] * purse;
            arr2[i] = sequence[i] * purse * -1;
            purse *= -1;
        }
        
        long ret1 = func(arr1);
        long ret2 = func(arr2);
        
        return Math.max(ret1, ret2);
    }
    
    public long func(int[] arr) {
        int N = arr.length;
        long[] sum = new long[N];
        long max = sum[0] = arr[0];
        
        for(int i=1; i<N; i++) {
            sum[i] = Math.max(sum[i-1] + arr[i], arr[i]);
            max = Math.max(max, sum[i]);
        }
        
        return max;
    }
}