import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_PG_42628 {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
//        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println((new Solution()).solution(operations));
    }

    static class Solution {
        public int[] solution(String[] operations) {
            int[] answer = {0, 0};

            Queue<Integer> minHeap = new PriorityQueue<>();
            Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            char cmd;
            int K = operations.length, num;
            for(String op: operations){
                cmd = op.split(" ")[0].charAt(0);
                num = Integer.parseInt(op.split(" ")[1]);

                switch (cmd){
                    case 'I':
                        minHeap.add(num);
                        maxHeap.add(num);
                        break;
                    case 'D':
                        if(maxHeap.size()==0) continue;
                        if(num>0){ // 최댓값
                            minHeap.remove(maxHeap.poll());
                        } else if(num<0) { // 최솟값
                            maxHeap.remove(minHeap.poll());
                        }
                        break;
                }
            }

            if(maxHeap.size()>0){
                answer[0] = maxHeap.peek();
                answer[1] = minHeap.peek();
            }

            return answer;
        }
    }
}
