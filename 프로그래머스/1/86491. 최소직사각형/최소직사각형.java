import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        
        int max = -1, min = -1;
        for(int[] pair: sizes) {
            max = Math.max(max, Math.max(pair[0], pair[1]));
            min = Math.max(min, Math.min(pair[0], pair[1]));
        }
        
        return max * min;
    }
}