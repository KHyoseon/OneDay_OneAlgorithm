import java.io.*;
import java.util.Stack;

public class Main_BJ_10773_제로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++){
            String string = br.readLine();
            if("0".equals(string)){
                stack.pop();
                continue;
            }
            stack.add(Integer.parseInt(string));
        }

        for(int s: stack){
            sum += s;
        }

        System.out.println(sum);
    }
}
