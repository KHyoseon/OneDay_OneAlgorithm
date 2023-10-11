import java.util.Arrays;

class Solution {
    int[] times;
    int N;
    public long solution(int n, int[] t) {
        this.N = n;
        this.times = t;

        // 오름차순 정렬
        Arrays.sort(times);

        long lo = times[0], hi = (long) times[0] *n;
        long mid;
        while (lo+1 < hi){
            mid = (lo+hi)/2;
            if(check(mid)==check(lo))
                lo = mid;
            else
                hi = mid;
        }

        return hi;
    }

    public boolean check(long m){
        int nn = N;
        for(int t: times){
            nn -= m/t;
            if(nn<=0) return true;
        }
        return false;
    }
}