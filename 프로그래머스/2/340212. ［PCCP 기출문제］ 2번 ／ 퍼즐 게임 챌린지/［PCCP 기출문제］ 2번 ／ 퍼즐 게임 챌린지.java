class Solution {
    int[] diffs;
    int[] times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        int max = 0;        
        for(int d: diffs)
            max = Math.max(max, d);
        
        int answer = getMinLevel(1, max);
        return answer;
    }
    
    public int getMinLevel(int l, int r) {
        if(psb(l)) return l;
        while(l+1 < r) {
            int m = (l+r)/2;
            if(psb(l) == psb(m))    l = m;
            else    r = m;
        }
        return r;
    }
    
    public boolean psb(int level) {
        int prev = 0;
        int cur;
        long time = 0;
        
        for(int i=0, l=times.length; i<l; i++) {
            if(time > limit) return false;
            
            cur = times[i];
            if(level - diffs[i] < 0) {
                time += (diffs[i]-level) * (cur + prev);
            }
            time += cur;
            prev = cur;
        }
        
        return time <= limit;
    }
}