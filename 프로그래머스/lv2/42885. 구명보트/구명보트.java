import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int cnt = 0;
        int n = people.length;
        int l=0, r=n-1;

        int exit = 0;

        while(exit < n) {
            ++exit;
            if (people[l] + people[r] <= limit) {
                ++l;
                ++exit;
            }
            --r;
            ++cnt;
        }

        return cnt;
    }
}