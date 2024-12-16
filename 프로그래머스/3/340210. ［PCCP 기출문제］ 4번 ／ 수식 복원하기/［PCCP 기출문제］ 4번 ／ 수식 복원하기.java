import java.util.*;

class Solution {
    ArrayList<Exp> mystery = new ArrayList<>();
    ArrayList<Exp> list = new ArrayList<>();

    public String[] solution(String[] expressions) {
        int max = 0;

        for(String exp: expressions) {
            String[] e = exp.split(" "); // op1,operator,op2,=,result

            int num1 = Integer.parseInt(e[0]);
            while(num1 > 0) {
                max = Math.max(max, num1 % 10);
                num1 /= 10;
            }
            int num2 = Integer.parseInt(e[2]);
            while(num2 > 0) {
                max = Math.max(max, num1 % 10);
                num2 /= 10;
            }

            if("X".equals(e[4]))
                mystery.add(new Exp(Integer.parseInt(e[0]), Integer.parseInt(e[2]), -1, e[1].charAt(0)));
            else
                list.add(new Exp(Integer.parseInt(e[0]), Integer.parseInt(e[2]), Integer.parseInt(e[4]), e[1].charAt(0)));
        }

        ArrayList<Integer> digits = new ArrayList<>();
        for(int i=max+1; i<10; i++) {
            if(!isOk(i)) continue;
            digits.add(i);
        }

        ArrayList<String> answer = new ArrayList<>();

        for(Exp exp: mystery) {
            for (int digit: digits) {
                int ret = exp.calc(digit);
                if(exp.result != -1 && exp.result != ret) {
                    exp.result = -1;
                    break;
                }
                exp.result = ret;
            }
            answer.add(String.format("%d %c %d = %s", exp.op1, exp.operator, exp.op2, (exp.result==-1? "?": exp.result+"")));
        }


        return answer.stream().toArray(String[]::new);
    }

    private boolean isOk(int digit) {
        for(Exp exp: list) {
            if(!exp.canBeTrue(digit)) return false;
        }
        return true;
    }

    class Exp {
        int op1, op2, result;
        char operator;


        public Exp(int op1, int op2, int result, char operator) {
            this.op1 = op1;
            this.op2 = op2;
            this.result = result;
            this.operator = operator;
        }

        public int calc(int digit) {
            int operand1 = convertToTen(digit, op1);
            int operand2 = convertToTen(digit, op2);
            if (operator == '+')
                return convertToDigit(digit, operand1 + operand2);
            else
                return convertToDigit(digit, operand1 - operand2);
        }

        public boolean canBeTrue(int digit) {
            return result == calc(digit);
        }
    }

    private static int convertToTen(int digit, int number) {
        int ret = 0, i = 0;
        while(number > 0) {
            ret += (number % 10) * Math.pow(digit, i++);
            number /= 10;
        }
        return ret;
    }

    private static int convertToDigit(int digit, int number) {
        if(number == 0) return 0;
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            sb.append(number % digit);
            number /= digit;
        }
        return Integer.parseInt(sb.reverse().toString());
    }
}