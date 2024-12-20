import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    int[] priority = new int[3];
    long MAX = 0;
    String[] splited;

    public long solution(String expression) {
        splited = expression.split("((?=[\\+\\-\\*])|(?<=[\\+\\-\\*]))");
        // System.out.println(splited.length + " " +Arrays.toString(splited));

        map.put("*", 0);
        map.put("+", 1);
        map.put("-", 2);

        comb(0, new boolean[3]);
        return MAX;
    }

    public void comb(int cnt, boolean[] visited) {
        if(cnt == 3) {
            // System.out.printf("%s=>\t", Arrays.toString(priority));
            long ret = calc();
            // System.out.print(ret + " VS " + MAX + "=>\t");
            MAX = Math.max(MAX, Math.abs(ret));
            // System.out.println(MAX);
            return;
        }
        for(int i=0; i<3; i++) {
            if(visited[i]) continue;

            priority[cnt] = i;

            visited[i] = true;
            comb(cnt+1, visited);
            visited[i] = false;
        }
    }

    public long calc() {
        Stack<Long> operand = new Stack<>();
        Stack<String> operator = new Stack<>();

        operand.push(Long.parseLong(splited[0]));

        for (int s = 1, l = splited.length; s < l; s++) {
            String str = splited[s];

            int me = map.get(str);
            long next = Long.parseLong(splited[++s]);

            while (!operator.isEmpty()) {
                String peek = operator.peek();

                // 스택에 있는게 나보다 우선 순위 낮으면 그대로 push
                if (priority[map.get(peek)] >= priority[me]) break;

                // 나보다 우선 순위 높은 애가 스택에 있으면 걔 먼저 처리
                long n1, n2, ret;

                n2 = operand.pop();
                n1 = operand.pop();
                operator.pop();

                // 만약 -면 절댓값을 높이기 위해 맨 첫번째 -제외 나머지는 +로 더함
                if (peek.equals("-")) {
                    // 앞에 계속 빼기임
                    if (!operator.isEmpty() && "-".equals(operator.peek())) {
                        ret = calc(n1, n2, "+");
                        operand.push(ret);
                    } else {
                        // 이번 빼기가 제일 첫번째 빼기일 경우
                        ret = calc(n1, n2, peek);
                        operand.push(ret);
                    }
                } else {
                    ret = calc(n1, n2, peek);
                    operand.push(ret);
                }
            }
            operand.push(next);
            operator.push(str);
        }

        while (!operator.isEmpty()) {
            long n1, n2, ret;
            String pop = operator.pop();

            n2 = operand.pop();
            n1 = operand.pop();

            // 만약 -면 절댓값을 높이기 위해 맨 첫번째 -제외 나머지는 +로 더함
            if (pop.equals("-")) {
                // 앞에 계속 빼기임
                if (!operator.isEmpty() && "-".equals(operator.peek())) {
                    ret = calc(n1, n2, "+");
                    operand.push(ret);
                } else {
                    // 이번 빼기가 제일 첫번째 빼기일 경우
                    ret = calc(n1, n2, pop);
                    operand.push(ret);
                }
            } else {
                ret = calc(n1, n2, pop);
                operand.push(ret);
            }
        }
//    }

        return operand.pop();
    }

    public long calc(long n1, long n2, String peek) {
        if("*".equals(peek)) return n1*n2;
        if("+".equals(peek)) return n1+n2;
        return n1-n2;
    }
}