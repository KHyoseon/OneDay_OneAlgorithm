import java.util.*;

class Solution {
    int[][] points;

    class Robot {
        int r, c;
        int[] route;
        int idx;

        public Robot(int[] route) {
            int[] target = points[route[idx] - 1];
            this.r = target[0];
            this.c = target[1];
            this.route = route;
            this.idx++;
        }

        public void move() {
            if(idx >= route.length) return;
            int[] target = points[route[idx] - 1];

            if(this.r != target[0]){
                if(this.r < target[0])  this.r++;
                else                    this.r--;
            } else if(this.c != target[1]) {
                if(this.c < target[1])  this.c++;
                else                    this.c--;
            }
            if(this.r == target[0] && this.c == target[1]) {
                this.idx++;
            }
        }

        public boolean isArrived() {
            return this.idx >= this.route.length;
        }

        public int pos(){
            return r*100 + c;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        this.points = points;

        int R = routes.length;

        Queue<Robot> robots = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap();

        int crashed = 0;

        for(int r=0; r<R; r++) {
            robots.add(new Robot(routes[r]));

            if (map.containsKey(routes[r][0]) && map.get(routes[r][0]) == 1) crashed++;

            if (!map.containsKey(routes[r][0]))
                map.put(routes[r][0], 0);
            map.put(routes[r][0], map.get(routes[r][0]) + 1);
        }

        int time = 0;
        while(!robots.isEmpty()) {
            int size = robots.size();
            map.clear();
            while (size > 0) {
                Robot robot = robots.poll();
                robot.move();

                int pos = robot.pos();

                // 그 위치에서 처음으로 충돌이 나면 카운트
                if (map.containsKey(pos) && map.get(pos) == 1) crashed++;

                // 위치 표시
                if (!map.containsKey(pos))
                    map.put(pos, 0);
                map.put(pos, map.get(pos) + 1);

                // 도착했으면 제외함
                if (!robot.isArrived())
                    robots.add(robot);
                --size;
            }
        }

        return crashed;
    }
}