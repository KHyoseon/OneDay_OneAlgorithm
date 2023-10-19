import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long sum = 0;
        Stack<Integer> stack = new Stack<>();
        for(int building: arr) {
            if(stack.isEmpty()) {
                stack.add(building);
                continue;
            }

            while(!stack.isEmpty() && stack.peek() <= building) {
                stack.pop();
            }
            sum += stack.size();
            stack.add(building);
        }

        System.out.println(sum);
    }

}