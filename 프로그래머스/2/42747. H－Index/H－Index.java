import java.util.*;

class Solution {
    static int[] citations;
    public int solution(int[] citations) {
        this.citations = citations;
        Arrays.sort(citations);

        int l=0, r=citations[citations.length-1];
        int m;

        while(l+1<r) {
            m = (l+r)/2;
            if(condition(l) == condition(m)) l=m;
            else r=m;
        }

        return l;
    }

    public boolean condition(int h) {
        int cnt=0;
        for(int i=citations.length-1; i>=0; i--) {
            if(citations[i] < h) break;
            cnt++;
        }
        return h<=cnt;
    }
}