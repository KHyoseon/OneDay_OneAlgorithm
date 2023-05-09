import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (o1, o2) -> o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]);

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        heap.add(jobs[0]);

        int[] nowPlaying;
        int currentTime = jobs[0][0];
        int answer = 0, next = 1;

        while(true) {
            // 대기 중인 일이 있을 때
            while (!heap.isEmpty()) {
                // 지금 재생할 수 있는 탐색 실행
                nowPlaying = heap.poll();
                currentTime += nowPlaying[1];
                answer += (currentTime - nowPlaying[0]);

                // 다음 작업 선택
                for (; next < n; next++) {
                    if (jobs[next][0] > currentTime) break;
                    heap.add(jobs[next]);
                }
            }
            if(next >= n) break;
            // 대기 중인 일이 없을 때
            nowPlaying = jobs[next++];
            currentTime = nowPlaying[0];
            heap.add(nowPlaying);
        }

        return answer / n;
    }
}