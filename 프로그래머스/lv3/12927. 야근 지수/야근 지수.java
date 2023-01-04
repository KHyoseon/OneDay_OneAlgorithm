import java.util.PriorityQueue;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for(int w: works){
            queue.add(w);
        }

        int max;
        while(n>0 && !queue.isEmpty()){
            max = queue.poll();
            if(max-1 > 0)
                queue.add(max-1);
            n--;
        }

        for(long r: queue){
            answer += r *r;
        }

        return answer;
    }
}