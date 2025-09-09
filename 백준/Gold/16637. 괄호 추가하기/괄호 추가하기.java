import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        boolean num = true;
        for(char c: br.readLine().toCharArray()) {
            if(num) numbers.add(c - '0');
            else    operators.add(c);
            num = !num;
        }

        dfs(0, numbers.get(0), numbers, operators);

        System.out.println(MAX);
    }

    static int MAX = Integer.MIN_VALUE;
    private static void dfs(int index, int sum, ArrayList<Integer> numbers, ArrayList<Character> operators) {
        if(index >= operators.size()) {
            MAX = Math.max(MAX, sum);
            return;
        }

        // 다음에 괄호 없으면 이번 거부터 계산하고 다음으로 진행
        int cur1 = calc(sum, numbers.get(index + 1), operators.get(index));

        dfs(index + 1, cur1, numbers, operators);

        // 다음에 괄호 있으면 다음 거부터 계산하고 이번 거까지 계산 후 진행
        if(index+1 < operators.size()) {
            int next = calc(numbers.get(index + 1), numbers.get(index + 2), operators.get(index + 1));
            int cur2 = calc(sum, next, operators.get(index));
            dfs(index + 2, cur2, numbers, operators);
        }
    }

    private static int calc(int n1, int n2, char op) {
        if(op == '+')        return n1+n2;
        else if(op == '-')   return n1-n2;
        else                 return n1*n2;
    }
}