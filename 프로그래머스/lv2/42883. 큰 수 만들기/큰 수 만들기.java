class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int n = number.length();
        int next = 0;
        int window = k+1;

        for(int l=0, ll=n-k; l<ll; l++) {
            int max = -1;
            for (int i=next, r=sb.length()+window; i<r && i<n; i++) {
                if (number.charAt(i)-'0' > max) {
                    max = number.charAt(i) - '0';
                    next = i+1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}