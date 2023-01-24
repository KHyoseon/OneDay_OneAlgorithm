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
    
    public String Sol2(String number, int k){
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}
