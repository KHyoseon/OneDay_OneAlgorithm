import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        while(!"0".equals(input)) {
            StringTokenizer st = new StringTokenizer(input);

            // 전역 변수 초기화
            int N = Integer.parseInt(st.nextToken());
            long MAX = 0;
            int[] heights = new int[N + 2];

            // 0, heights[1~N], 0
            for (int i = 1; i <= N; i++)
                heights[i] = Integer.parseInt(st.nextToken());

            Stack<Integer> stack = new Stack<>();
            stack.push(0);

            for(int i=1; i<N+2 && !stack.isEmpty(); i++) {
                // stack에 오름차순으로 집어넣음
                while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    int j = stack.pop();
                    long w = i - stack.peek() - 1;
                    long h = heights[j];
                    MAX = Math.max(MAX, w * h);
                }
                stack.push(i);
            }

            sb.append(MAX).append("\n");

            input = br.readLine();
        }

        System.out.print(sb);
    }

}