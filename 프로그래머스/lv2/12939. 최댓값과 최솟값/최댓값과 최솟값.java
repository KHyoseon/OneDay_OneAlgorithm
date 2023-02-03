
import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s, " ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int now;
        while (st.hasMoreTokens()){
            now = Integer.parseInt(st.nextToken());
            max = Math.max(max, now);
            min = Math.min(min, now);
        }

        return min+" "+max;
    }
}