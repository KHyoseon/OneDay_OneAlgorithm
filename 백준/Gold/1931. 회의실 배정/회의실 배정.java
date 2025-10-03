import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Stack<int[]> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            times[i][0] = Integer.parseInt(input[0]);
            times[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(times, ((o1, o2) -> o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]));

        for(int i=0; i<N; i++) {
            if(stack.isEmpty()) {
                stack.push(times[i]);
                continue;
            }
            if(stack.peek()[1] <= times[i][0]) {
                stack.push(times[i]);
            } else if(stack.peek()[1] > times[i][1]) {
                stack.pop();
                stack.push(times[i]);
            }

        }
        System.out.println(stack.size());
    }
}