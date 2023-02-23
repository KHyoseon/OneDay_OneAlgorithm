class Solution {
    Rect[] rects;
    int MIN = Integer.MAX_VALUE;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        rects = new Rect[rectangle.length];

        for(int i=0, l= rectangle.length; i<l; i++) {
            rects[i] = new Rect(rectangle[i]);
        }

        visited = new boolean[101][101];
        visited[characterX*2][characterY*2] = true;
        findWay(characterX*2, characterY*2, itemX*2, itemY*2, 0);
        return MIN/2;
    }

    // 상좌하우
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    boolean[][] visited;

    private void findWay(int x, int y, int itemX, int itemY, int dist) {
        if(itemX == x && itemY == y) {
            MIN = Math.min(MIN, dist);
            return;
        }
        int nx, ny;
        for(int d=0; d<4; d++) {
            nx = x + dx[d];
            ny = y + dy[d];

            if(nx < 1 || nx > 100 || ny < 1 || ny > 100) continue;
            if(!visited[nx][ny] && isOnEdgeLine(nx, ny)) {
                visited[nx][ny] = true;
                findWay(nx, ny, itemX, itemY, dist + 1);
                visited[nx][ny] = false;
            }
        }
    }

    private boolean isOnEdgeLine(int x, int y) {
        boolean outOfAllRects = true;

        for(Rect rect: rects) {
            switch (rect.relationWithDot(x, y)){
                // -1: 밖에
                case 0: // 선 위에
                    outOfAllRects = false;
                    break;
                case 1: // 안에
                    return false;
            }
        }

        if(outOfAllRects) return false;
        return true;
    }

    class Rect {
        int minX, maxX;
        int minY, maxY;

        public Rect(int[] dots) {
            this.minX = dots[0]*2;
            this.minY = dots[1]*2;
            this.maxX = dots[2]*2;
            this.maxY = dots[3]*2;
        }

        public int relationWithDot(int x, int y) {
            // 사각형 밖에 존재
            if (minX > x || x > maxX ||
                    minY > y || y > maxY) return -1;
            // 사각형 안에 존재
            if(minX < x && x < maxX &&
                minY < y && y < maxY) return 1;
            // 사각형 라인 위에 존재
            return 0;
        }
    }
}