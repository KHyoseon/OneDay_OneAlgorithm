class Solution {
    int[][] dungeons;

    public int solution(int k, int[][] dg) {
        dungeons = dg;
        return comb(k, 0);
    }

    private int comb(int k, int flag) {
        int ret = 0;
        for(int i=0, n=dungeons.length; i<n; i++) {
            if((flag & 1<<i) != 0) continue;
            if(k < dungeons[i][0]) continue;
            ret = Math.max(ret, comb(k-dungeons[i][1], flag | 1<<i) + 1);
        }
        return ret;
    }
}