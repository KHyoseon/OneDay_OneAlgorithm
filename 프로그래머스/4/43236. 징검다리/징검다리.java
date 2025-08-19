import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int l = 0, r = distance+1;
        int mid;
        
        while(l+1 < r) {
            mid = (l+r)/2;
            if(isPossible(mid, n, rocks, distance))
                l = mid;
            else
                r = mid;
        }
        return l;
    }
    
    public boolean isPossible(int dist, int n, int[] rocks, int distance) {
        int prev = 0;
        int remove = 0;
        
        for(int cur: rocks) {
            if(cur-prev >= dist){
                prev = cur;
                continue;
            }
            remove++;
            if(remove > n) return false;
        }
        
        if(distance - prev < dist)
            remove++;
        
        // System.out.printf("min = %d) removed: %d\n", dist, remove);
        
        if(remove > n) return false;
        return true;
    }
}