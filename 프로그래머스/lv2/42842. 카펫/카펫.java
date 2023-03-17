class Solution {
    public int[] solution(int brown, int multiple) {
        int[] answer = new int[2];
        int plus = (brown - 4) / 2;
        for(int n=1; n<=Math.sqrt(multiple); n++) {
            if(multiple % n != 0) continue;
            int m = multiple / n;
            if(n + m == plus){
                answer[0] = Math.max(n, m) + 2;
                answer[1] = Math.min(n, m) + 2;
                break;
            }
        }
        return answer;
    }
}