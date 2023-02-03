class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[prices.length];

        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N ; j++) {
                answer[i]++;
                if(prices[i] > prices[j]) break;
            }
        }

        return answer;
    }
}