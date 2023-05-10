import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char ch: str.toCharArray()) {
            if(65<= ch && ch <= 90) {
                sb.append(ch);
            } else {
                switch (ch) {
                    case '-':
                    case '+':
                        while(!stack.isEmpty() && stack.peek()!='(') {
                            sb.append(stack.pop());
                        }
                        stack.add(ch);
                        break;
                    case '*':
                    case '/':
                        while(!stack.isEmpty() && (stack.peek()=='*' || stack.peek()=='/')) {
                            sb.append(stack.pop());
                        }
                        stack.add(ch);
                        break;
                    case '(':
                        stack.add(ch);
                        break;
                    case ')':
                        while(!stack.isEmpty() && stack.peek()!='(') {
                            sb.append(stack.pop());
                        }
                        stack.pop();
                        break;
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

}