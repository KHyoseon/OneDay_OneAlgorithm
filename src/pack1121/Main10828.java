import java.io.*;
import java.util.Stack;

public class Main10828 {
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            switch (input[0]){
                case "push":
                    push(input[1]);
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "top":
                    top();
                    break;
            }
        }
    }

    private static void push(String str){
        stack.push(Integer.parseInt(str));
    }

    private static void pop(){
        if(stack.size() == 0) System.out.println("-1");
        else System.out.println(stack.pop());
    }

    private static void size(){
        System.out.println(stack.size());
    }

    private static void empty(){
        if(stack.empty()) System.out.println(1);
        else System.out.println(0);
    }

    private static void top(){
        if(stack.size() == 0) System.out.println("-1");
        else System.out.println(stack.peek());
    }

}
