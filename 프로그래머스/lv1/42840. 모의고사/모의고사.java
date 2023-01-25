import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    public static int[] solution(int[] answers) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->
            o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);

        int one=0, two=0, three=0;
        int[] a1 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        int[] a2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] a3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int i=0, j=0;
        for(int q: answers){
            if(q==a1[i%10]) one++;
            if(q==a2[j%8]) two++;
            if(q==a3[i%10]) three++;
            i++;
            j++;
        }

        pq.add(new int[]{1, one});
        pq.add(new int[]{2, two});
        pq.add(new int[]{3, three});

        ArrayList<Integer> list = new ArrayList<>();
        int max[] = pq.poll();
        list.add(max[0]);
        while(!pq.isEmpty() && pq.peek()[1] == max[1]) {
            list.add(pq.poll()[0]);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}