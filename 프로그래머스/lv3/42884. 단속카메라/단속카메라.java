import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2)-> o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]);

        int skip = 1;
        int length = routes.length;
        for(int cur=0; cur<length;){
            int right = routes[cur][1];
            for(int next=cur+1; next<length && !(routes[cur][1]<routes[next][0]) && routes[next][0] <= right; next++) {
                // next가 cur 범위에 포함됨
                skip++;
                right = Math.min( right, routes[next][1]);
            }
            // next가 cur 범위에 포함되지 않음
            answer++;
            cur += skip;
            skip=1;
        }
        
        return answer;
    }
}