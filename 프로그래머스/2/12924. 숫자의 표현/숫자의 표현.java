class Solution {
    public int solution(int x) {
        int answer = 0;
        
        int n = 1, m = x;
        while(true) {
            if((x - n*(n-1)/2) % n == 0) {
                m = (x - n*(n-1)/2) / n;
                if (m < 1) break;
                ++answer;
                if (m == 1) break;
            }
            ++n;
        }
        
        return answer;
    }
}