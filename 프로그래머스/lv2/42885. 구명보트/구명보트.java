import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int cnt = 0;
        int exit = 0;
        int n = people.length;
        for(int r= n-1, l=0; l<r && exit <= n; r--, exit++, cnt++) {
            if(people[l] + people[r] <= limit) {
                l++;
                exit++;
            }
        }
        cnt += (n - exit);
        return cnt;
    }
}