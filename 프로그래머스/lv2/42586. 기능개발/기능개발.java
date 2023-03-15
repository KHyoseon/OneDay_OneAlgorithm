import java.util.ArrayList;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] take = new int[n];

        int remain;
        for(int i=0; i<n; i++) {
            remain = 100 - progresses[i];
            take[i] = (remain / speeds[i]) + (remain % speeds[i] == 0 ? 0 : 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        int cnt = 1;
        int until = take[0];

        for(int i=1; i<n; i++) {
            if(until < take[i]) {
                list.add(cnt);
                cnt = 1;
                until = take[i];
                continue;
            }
            ++cnt;
        }
        
        list.add(cnt);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}