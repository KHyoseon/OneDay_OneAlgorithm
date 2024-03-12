import java.util.*;

class Solution {
    ArrayList<String> set = new ArrayList<>();
    char[] vowel = {'A', 'E', 'I', 'O', 'U'};
    StringBuilder sb = new StringBuilder();
    
    public int solution(String word) {
        comb(0);
        return set.indexOf(word) + 1;
    }
    
    private void comb(int length) {
        if(length >= 5) return;

        for(int i=0; i<5; i++) {
            sb.append(vowel[i]);

            set.add(sb.toString());
            comb(length+1);
            sb.setLength(sb.length()-1);
        }
    }
}