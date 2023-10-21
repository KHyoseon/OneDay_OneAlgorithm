import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] alpha = new int[26];
        Arrays.fill(alpha, Integer.MAX_VALUE);
        
        for(String key: keymap) {
            char[] arr = key.toCharArray();
            for(int i=0, l=arr.length; i<l; i++) {
                alpha[arr[i]-'A'] = Math.min(alpha[arr[i]-'A'], i+1);
            }
        }
        
        int answer[] = new int[targets.length];
        int a=-1;
        for(String target: targets) {
            char[] arr = target.toCharArray();
            ++a;
            if(a >= targets.length) break;
            for(char al: arr) {
                if(alpha[al-'A'] == Integer.MAX_VALUE) {
                    answer[a] = -1;
                    break;
                }
                answer[a] += alpha[al-'A'];
            }
        }
        
        return answer;
    }
}