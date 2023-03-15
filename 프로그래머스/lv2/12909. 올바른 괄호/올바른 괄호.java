class Solution {
    public boolean solution(String s) {
        int l = s.length();
        if(l%2!=0) return false;
        if(s.charAt(0) == ')' || s.charAt(l-1) == '(') return false;

        int stack = 0;
        for(char ch: s.toCharArray()) {
            switch (ch) {
                case '(':
                    ++stack;
                    break;
                case ')':
                    if(stack==0) return false;
                    --stack;
            }
        }

        return stack==0;
    }
}
