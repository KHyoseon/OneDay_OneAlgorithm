import java.util.Arrays;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n+1];
        Arrays.fill(students, 1);
        students[0] = 0;

        for(int rs: reserve){
            students[rs]++;
        }
        for(int ls: lost){
            students[ls]--;
        }

        Arrays.sort(lost);
        for(int ls: lost){
            if(students[ls]>0) continue;
            if(ls-1>=0 && students[ls-1]>1){
                students[ls-1]--;
                students[ls]++;
            } else if(ls+1<=n && students[ls+1]>1) {
                students[ls+1]--;
                students[ls]++;
            }
        }

        for(int cloth: students)
            if(cloth > 0) answer++;

        return answer;
    }
}