import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    int MIN = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        // 그래프 구성
        for(int i=0; i<n+1; i++) {
            tree.add(new ArrayList<>());
        }
        for(int[] wire: wires) {
            tree.get(wire[0]).add(wire[1]);
            tree.get(wire[1]).add(wire[0]);
        }

        // 그래프 하나씩 자르기
        int ret1, ret2;
        for(int[] cut: wires) {
            // 두 영역 노드 수 구하기
            tree.get(cut[0]).remove((Integer) cut[1]);
            tree.get(cut[1]).remove((Integer) cut[0]);

            ret1 = getNodeCnt(cut[0], n);
            ret2 = getNodeCnt(cut[1], n);

            MIN = Math.min(MIN, Math.abs(ret1 - ret2));

            tree.get(cut[0]).add(cut[1]);
            tree.get(cut[1]).add(cut[0]);
        }

        return MIN;
    }

    private int getNodeCnt(int root, int n) {
        Queue<Integer> group = new LinkedList<>();
        group.add(root);

        int temp;
        int cnt = 0;
        boolean[] visited = new boolean[n + 1];
        while (!group.isEmpty()) {
            temp = group.poll();
            if(visited[temp]) continue;
            ++cnt;
            visited[temp] = true;
            group.addAll(tree.get(temp));
        }

        return cnt;
    }
}