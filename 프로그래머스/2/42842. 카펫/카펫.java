class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int plus = (brown - 4) / 2;
        for(int n=1; n<=Math.sqrt(yellow); n++) {
            if(yellow % n != 0) continue;
            int m = yellow / n;
            if(n + m == plus){
                answer[0] = Math.max(n, m) + 2;
                answer[1] = Math.min(n, m) + 2;
                break;
            }
        }
        return answer;
    }
}