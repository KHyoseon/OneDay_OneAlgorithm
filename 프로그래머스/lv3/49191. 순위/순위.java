class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] map = new int[n][n];
        for(int[] result: results) {
            map[result[0]-1][result[1]-1] = 1;
            map[result[1]-1][result[0]-1] = -1;
        }
        
        for(int step=0; step<n; step++) {
            for(int start=0; start<n; start++) {
                if(step == start) continue;
                for(int end=0; end<n; end++) {
                    if(start==end || step==end) continue;
                    if(map[start][step]==1 && map[step][end]==1) {
                        map[start][end] = 1;
                        map[end][start] = -1;
                    }
                }
            }
        }
        
        int cnt;
        for(int[] line: map) {
            cnt = 0;
            for(int l: line)
                cnt += (l==0? 0: 1);            
            if(cnt==n-1)      answer++;
        }
        
        return answer;
    }
}