import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int[] stones, int k) {
        int l=0, r=200000001;
        int mid;

        while(l+1 < r) {
            mid = (l+r)/2;
            if (canGo(mid, stones, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean canGo(int people, int[] stones, int k) {
        int jump = 0;
        for(int canStep: stones){
            if(canStep < people){
                jump++;
                if(jump >= k) return false;
            }
            else {
                jump = 0;
            }
        }
        return true;
    }
}