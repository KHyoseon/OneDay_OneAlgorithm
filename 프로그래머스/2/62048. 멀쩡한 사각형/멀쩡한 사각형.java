class Solution {
    public long solution(int w, int h) {
        int min = Math.min(w, h);
        long max = Math.max(w, h);
        
        long answer = 0;
        
        for(int i=1; i<min; i++) {
            answer += (long)(max*i)/min;
        }
        
        return 2 * answer;
    }
}