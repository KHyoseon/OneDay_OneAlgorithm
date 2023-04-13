import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2)-> (o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]));
        
        // for(int[] target: targets) {
        //     System.out.printf("(%d, %d),  ", target[0], target[1]);
        // }
        // System.out.println();
        
        int S = 0, F = 0;
        
        for(int i=0, l=targets.length; i<l; i++) {
            if(F <= targets[i][0]) {
                answer++;
                S = targets[i][0];
                F = targets[i][1];
                // System.out.printf("(%d, %d)\n", S, F);
            } else {
                S = Math.min(S, targets[i][0]);
                F = Math.min(F, targets[i][1]);
            }
        }
        
        return answer;
    }
}