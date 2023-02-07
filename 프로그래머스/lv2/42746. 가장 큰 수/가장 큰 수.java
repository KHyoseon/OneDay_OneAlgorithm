import java.util.Arrays;
class Solution {
    public String solution(int[] numbers) {

        StringBuilder ans = new StringBuilder();
        String[] strings = new String[numbers.length];

        for(int i=0; i< numbers.length; i++) {
            strings[i] = numbers[i] + "";
        }

        Arrays.sort(strings, (o1, o2) -> o2.concat(o1).compareTo(o1.concat(o2)));

        for(String str: strings)
            ans.append(str);

        String answer = ans.toString();
        while (answer.charAt(0)=='0' && answer.contains("00")){
            answer = answer.replace("00", "0");
        }

        return answer;
    }
}