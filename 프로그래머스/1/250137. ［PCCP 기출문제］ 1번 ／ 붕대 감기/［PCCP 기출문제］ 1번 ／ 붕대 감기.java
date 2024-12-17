import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0});
            Collections.addAll(queue, attacks);

            int t = bandage[0];
            int x = bandage[1];
            int y = bandage[2];

            int max_health = health, time;
            int[] prev = queue.poll(), cur;

            while(!queue.isEmpty()) {
                cur = queue.poll();
                time = cur[0] - prev[0] - 1;

                health += ((time/t) * y + time * x);
                health = Math.min(max_health, health);
                // System.out.printf("%d초 동안 회복: %d => 총 피: %d\n", time, ((time/t) * y + t * x), health);

                health -= cur[1];
                if(health <= 0) {
                    return -1;
                }
                // System.out.printf("%d 피해 => 총 피: %d\n", cur[1], health);

                prev = cur;
            }

            return health;
    }
}