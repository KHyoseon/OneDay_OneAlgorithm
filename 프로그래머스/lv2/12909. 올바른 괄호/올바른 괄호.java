import java.util.Stack;
class Solution {
    public boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        int l = s.length();
        if(l%2!=0) return false;
        if(s.charAt(0) == ')' || s.charAt(l-1) == '(') return false;

        for(char ch: s.toCharArray()) {
            switch (ch) {
                case '(':
                    stack.add(ch);
                    break;
                case ')':
                    if(stack.isEmpty()) return false;
                    stack.pop();
            }
        }

        return stack.isEmpty();
    }
}