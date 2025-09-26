import java.util.Arrays;

class Solution {
    public int solution(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int max = 1;
        
        // 홀수 길이 중심 확장 (center = i)
        for (int i = 0; i < n; i++) {
            int L = i, R = i;
            while (L >= 0 && R < n && a[L] == a[R]) {
                max = Math.max(max, R - L + 1);
                L--; R++;
            }
        }

        // 짝수 길이 중심 확장 (center = i, i+1)
        for (int i = 0; i < n - 1; i++) {
            int L = i, R = i + 1;
            while (L >= 0 && R < n && a[L] == a[R]) {
                max = Math.max(max, R - L + 1);
                L--; R++;
            }
        }
        
        return max;
    }
}