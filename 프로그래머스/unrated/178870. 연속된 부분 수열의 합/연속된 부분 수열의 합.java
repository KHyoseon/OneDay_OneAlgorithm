class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        
        int sum = 0;
        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;
        
        // 모든 시작 위치에서
        int s=0, e=0;
        for(; s<n; s++) {
            
            for(; e<n; e++) {
                sum += sequence[e];
                // System.out.printf("현재 [%d, %d] = %d\n", s, e, sum);
                
                // 합이 k보다 작음 -> 계속 진행
                if(sum < k){
                    continue;
                } 
                
                // 합이 k보다 큼
                while(sum > k && s<n && s<e) {
                    // System.out.printf("k보다 크다 [%d, %d] = %d\n", s, e, sum);
                    sum -= sequence[s++];
                }
                
                // 합이 k와 같으면
                if(sum == k) {
                    // System.out.printf("딱 됨[%d, %d] = %d\n", s, e, sum);
                    // 배열 길이가 짧거나 길이는 같은데 시작점이 더 빠를 때
                    if(minLength > e-s || (minLength == e-s && answer[0] > s)) {
                        // System.out.printf("갱신 [%d, %d] = %d\n", s, e, sum);
                        minLength = e-s;
                        answer[0] = s;
                        answer[1] = e;
                        sum -= sequence[s++];
                        // System.out.printf("앞에거 뺌 [%d, %d] = %d\n", s, e, sum);
                    }
                }
            }
        }
        
        return answer;
    }
}