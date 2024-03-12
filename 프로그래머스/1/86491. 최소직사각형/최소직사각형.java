import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        
        for(int i=0; i<sizes.length; i++) {
            if(sizes[i][0] < sizes[i][1]) {
                int temp = sizes[i][1];
                sizes[i][1] = sizes[i][0];
                sizes[i][0] = temp;
            }
        }
        
        int max = -1, min = -1;
        for(int[] pair: sizes) {
            max = Math.max(max, pair[0]);
            min = Math.max(min, pair[1]);
        }
        
        return max * min;
    }
}