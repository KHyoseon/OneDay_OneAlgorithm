class Solution {
    public int solution(int[] a) {
        int l = a.length;

        int[] left = new int[l];
        int[] right = new int[l];

        left[0] = a[0];
        right[l-1] = a[l-1];
        
        for(int i=1; i<l; i++) {
            left[i] = Math.min(left[i-1], a[i]);
            right[l-1-i] = Math.min(right[l-i], a[l-i]);
        }

        if(l==1) return 1;
        
        int answer = 2;
        for(int i=1; i<l-1; i++) {
            if(left[i] < a[i] && right[i] < a[i]) continue;
            answer++;
        }
        
        return answer;
    }
}