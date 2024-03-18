import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (o1, o2) -> o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]);

        int[] arr = {-30000, 30000};
        for(int i=0, N=routes.length; i<N; i++) {
            if(arr[0] <= routes[i][0] && routes[i][0] <= arr[1]) {
                arr[0] = routes[i][0];
                arr[1] = Math.min(routes[i][1], arr[1]);
                continue;
            }
            arr[0] = routes[i][0];
            arr[1] = routes[i][1];
            answer++;
        }

        return answer;
    }
}