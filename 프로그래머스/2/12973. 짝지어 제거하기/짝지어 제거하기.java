import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(char c: s.toCharArray()) {
            if(stack.isEmpty() || c != stack.peek())
                stack.push(c);
            else
                stack.pop();
        }

        return stack.size()==0? 1: 0;
    }
}