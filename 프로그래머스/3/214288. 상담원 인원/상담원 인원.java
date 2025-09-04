import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    int MIN = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        int[] counsel = new int[k];
        Arrays.fill(counsel, 1);
        comb(counsel, 0, n-k, reqs);
        return MIN;
    }

    private void comb(int[] counsel, int index, int remain, int[][] reqs) {
        if(index == counsel.length-1) {
            counsel[index] += remain;
            int ret = simulate(counsel, reqs);
            counsel[index] -= remain;
            MIN = Math.min(MIN, ret);
            return;
        }

        for(int i=0; i<=remain; i++) {
            counsel[index] += i;
            comb(counsel, index+1, remain - i, reqs);
            counsel[index] -= i;
        }
    }

    private int simulate(int[] counsel, int[][] reqs) {
        int k = counsel.length;

        PriorityQueue<int[]>[] mentees = new PriorityQueue[k];
        PriorityQueue<Integer>[] mentors = new PriorityQueue[k];

        for(int i=0; i<k; i++) {
            mentees[i] = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            mentors[i] = new PriorityQueue<>();
        }

        // mentees
        for(int[] req: reqs) {
            // [a, b, c] c번 유형의 상담을 원하는 참가자가 a분에 b분 동안의 상담을 요청
            mentees[req[2]-1].add(new int[]{req[0], req[1]});
        }

        // mento
        for(int c=0; c<k; c++) {
            for (int j = 0; j < counsel[c]; j++) {
                mentors[c].add(0);
            }
        }

        // simulate
        int sum = 0;
        for(int c=0; c<k; c++) {
            for(int[] mentee: mentees[c]) {
                if(mentors[c].isEmpty()) {
                    System.out.println("이럴수가있나");
                    return Integer.MAX_VALUE;
                }
                int endTime = mentors[c].poll();
                int startTime = Math.max(endTime, mentee[0]);

                sum += (startTime - mentee[0]);
                if(sum > MIN) return Integer.MAX_VALUE;

                endTime = startTime + mentee[1];
                mentors[c].add(endTime);
            }
        }

        return sum;
    }
}