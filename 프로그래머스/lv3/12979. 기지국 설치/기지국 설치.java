import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int dist, width = 2*w + 1;

        dist = stations[0] - w - 1;
        if(dist > 0) {
            answer += (dist%width != 0)? dist/width + 1: dist/width;
        }

        int l = stations.length;
        for(int i=1; i<l; i++) {
            dist = (stations[i] - w - 1) - (stations[i - 1] + w);
            if(dist > 0) {
                answer += (dist%width != 0)? dist/width + 1: dist/width;
            }
        }

        dist = n - (stations[l-1] + w);
        if(dist > 0) {
            answer += (dist%width != 0)? dist/width + 1: dist/width;
        }

        return answer;
    }
}