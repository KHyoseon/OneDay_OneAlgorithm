import java.util.Arrays;
class Solution {
    public int solution(String[] words) {
        int answer = 0;

        int N = words.length;
        Arrays.sort(words);

        int[] diff = new int[N - 1];

        diff[0] = distinguish(words[0], words[1]);

        for(int i=1; i<N-1; i++) {
            diff[i] = distinguish(words[i], words[i+1]);
            answer += Math.min(words[i].length(), Math.max(diff[i - 1], diff[i]));
        }

        answer += Math.min(words[0].length(), diff[0]);
        answer += Math.min(words[N-1].length(), diff[N - 2]);

        return answer;
    }

    private int distinguish(String str1, String str2) {
        int i=0;

        int l1 = str1.length();
        int l2 = str2.length();

        do {
            if (str1.charAt(i) != str2.charAt(i)) break;
            i++;
        } while (i<l1 && i<l2);
        return i+1;
    }
}