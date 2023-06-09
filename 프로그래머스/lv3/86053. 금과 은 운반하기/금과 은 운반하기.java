import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long min = 0L;
        long max = 400000000000000L;
        long mid;
        
        while(min+1 < max) {
            mid = (min+max) / 2;
            // System.out.printf("(%d) %d (%d) 시간에 가능?\n", min, mid, max);
            if(IsPossibleInTime(mid, a, b, g, s, w, t)) {
                // System.out.printf("===>가능\n\n");
                max = mid;
            } else {
                // System.out.printf("===>불가능\n\n");
                min = mid;
            }
        }
        
        return max;
    }
    
    public boolean IsPossibleInTime(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = g.length;
        
        long totalG = 0L;
        long totalS = 0L;
        long total = 0L;
        long maxG, maxS, maxT;
        
        long cnt;
        for(int i=0; i<n; i++) {
            cnt = time / (t[i]*2L);
            if(time % (t[i]*2L) >= t[i]) cnt++;
            // System.out.printf("%d번 왔다 갔다\n", cnt);
            
            maxG = Math.min(w[i] * cnt, g[i]);
            maxS = Math.min(w[i] * cnt, s[i]);
            maxT = Math.min(w[i] * cnt, g[i]+s[i]);
            
            totalG += maxG;
            totalS += maxS;
            total += maxT;
        }
            // System.out.printf("\t금 최대: %d, 은 최대: %d, 합쳐서 최대: %d 가능\n", totalG, totalS, total);
        if(totalG < a || totalS < b) return false;
        if(total < a+b) return false;
        return true;
    }
}