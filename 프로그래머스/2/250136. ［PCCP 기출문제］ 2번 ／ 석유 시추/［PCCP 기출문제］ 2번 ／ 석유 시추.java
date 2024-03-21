import java.util.*;
class Solution {
    
    int N, M;
    int[][] land;

    HashMap<Integer, Integer> map = new HashMap<>();

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        this.land = land;

        bfs();

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        pq.addAll(map.entrySet());

        Map.Entry<Integer, Integer> entry = pq.poll();

        return entry.getValue();

    }

    public void bfs() {
        boolean visited[][] = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(land[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, visited);
            }
        }
    }

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    public void bfs(int r, int c, boolean[][] visited) {
        // 구역 열 정보 저장
        HashSet<Integer> col = new HashSet<>();
        int cnt = 0;

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{r, c});
        visited[r][c] = true;
        cnt++;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 열 정보 저장
            if(!col.contains(cur[1])) col.add(cur[1]);
            // 다음 방문지 탐색
            int nr, nc;
            for(int d=0; d<4; d++){
                nr = dr[d] + cur[0];
                nc = dc[d] + cur[1];
                // 범위 밖, 방문 여부
                if(!canGo(nr, nc) || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                cnt++;
                queue.add(new int[]{nr, nc});
            }
        }

        // 탐색 끝나면 모든 행에 넓이 정보 넣음
        for(int cc: col) {
            if(!map.containsKey(cc)) map.put(cc, cnt);
            else map.put(cc, map.get(cc) + cnt);
        }
    }

    public boolean canGo(int r, int c) {
        if(r<0 || r>=N || c<0 || c>=M) return false;
        if(land[r][c]==0) return false;
        return true;
    }
}

/*
맵 bfs로 석유 부분 다 찾음 -> set에 열 번호 입력
TreeMap<열, 석유 크기> 해서 계속 더함.. 정렬
제일 큰 열 뽑아서 넣기
*/