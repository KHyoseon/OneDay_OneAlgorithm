import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        StringBuilder zero = new StringBuilder();

        for(int i=0, l= numbers.length; i<l; i++) {
            StringBuilder num = toBinary(numbers[i]);
            int h = (int) Math.ceil(Math.log(num.length() + 1) / Math.log(2));

            zero.setLength(0);
            for (int j = 0; j < Math.pow(2, h) - 1 - num.length(); j++) {
                zero.append("0");
            }
            num.insert(0, zero);

            int ret = check(num.toString(), 0, num.length() - 1);
            answer[i] = ret==-1? 0: 1;
        }
        return answer;
    }

    private int check(String str, int start, int end) {
        if(end < start) return -1;
        if(start == end) return str.charAt(start) - '0';

        int root = (start + end) / 2;

        int left = check(str, start, root-1);
        if(left == -1) return -1;

        int right = check(str, root + 1, end);
        if(right == -1) return -1;

        if(str.charAt(root) == '0' &&
                (left == 1 || right == 1)) return -1;

        return str.charAt(root) - '0';
    }

    private StringBuilder toBinary(long number) {
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            sb.append(number%2);
            number /= 2;
        }
        return sb.reverse();
    }
}