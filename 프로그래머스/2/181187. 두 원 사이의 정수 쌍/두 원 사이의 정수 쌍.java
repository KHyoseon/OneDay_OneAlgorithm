import java.math.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long rr1 = (long)Math.pow(r1, 2);
        long rr2 = (long)Math.pow(r2, 2);
        
        for(int c=0; c<r1; c++) {
            long cc = (long)Math.pow(c, 2);
            long h1 = (long)Math.ceil(Math.sqrt(rr1 - cc));
            long h2 = (long)Math.floor(Math.sqrt(rr2 - cc));
            answer += (h2 - h1 + 1);
            // System.out.printf("c=%d, %d개\n", c, h2-h1);
        }
        
        for(int c=r1; c<r2; c++) {
            long cc = (long)Math.pow(c, 2);
            answer += (long)Math.floor(Math.sqrt(rr2 - cc));
            // System.out.printf("c=%d, %d개\n", c, (long)Math.floor(Math.sqrt(rr2 - cc)));
        }
        
        answer *= 4;
        
        return answer;
    }
}