class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int n = number.length();

        int next = 0;
        for(int l=0; l<n-k; l++) {
            int max = -1;
            int sl = n-k - sb.length() - 1;
            for (int i = next; i < n - sl && i<n; i++) {
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