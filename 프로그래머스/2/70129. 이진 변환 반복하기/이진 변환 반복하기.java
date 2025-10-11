import java.util.*;

class Solution {
    public int[] solution(String s) {
        int cnt=0, zero=0;
        int num, one;
        String str = s;
        
        while(!str.equals("1")) {
            one = oneCount(str);
            zero += str.length() - one;
            str = intToStr(one);
            ++cnt;
        }
        return new int[]{cnt, zero};
    }
    
    public int oneCount(String str) {
        int one = 0;
        for(char c: str.toCharArray()) {
            if(c == '1') ++one;
        }
        return one;
    }
    
    public String intToStr(int i) {
        StringBuilder sb = new StringBuilder();
        while(i>0) {
            sb.append(i%2);
            i /= 2;
        }
        return sb.reverse().toString();
    }
}