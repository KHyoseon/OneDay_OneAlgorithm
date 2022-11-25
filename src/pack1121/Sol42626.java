import java.util.PriorityQueue;
import java.util.Queue;

public class Sol42626 {
    public static void main(String[] args) {
        int K = 7;
        int[] scoville = {1, 2, 3, 9, 10, 12};
        System.out.println((new Solution()).solution(scoville, K));
    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            Queue<Integer> heap = new PriorityQueue<>();

            for(int scov: scoville){
                heap.add(scov);
            }

            int min, min2, newOne;
            while(heap.peek()<K){
                min = heap.poll();
                if(heap.isEmpty())
                    return -1;
                min2 = heap.poll();
                newOne = min + 2 * min2;
                heap.add(newOne);
                ++answer;
            }

            return answer;
        }
    }
}
