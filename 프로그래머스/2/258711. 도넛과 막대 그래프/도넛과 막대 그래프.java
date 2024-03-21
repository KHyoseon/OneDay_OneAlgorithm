import java.util.*;


class Solution {
    public int[] solution(int[][] edges) {
        
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int[] edge : edges) {
            // edge[0]: out++, edge[1]:in++
            if (!map.containsKey(edge[0]))
                map.put(edge[0], new int[]{0,0});
            int[] cur = map.get(edge[0]);
            cur[1]++;
            map.put(edge[0], cur);

            if (!map.containsKey(edge[1]))
                map.put(edge[1], new int[]{0,0});
            cur = map.get(edge[1]);
            cur[0]++;
            map.put(edge[1], cur);
        }
        
        /*
        각 노드별,
        경우 1) out==0: ---> 직선 그래프 갯수 +=1
        경우 2) out==1: 흔한 노드.. 무시
        경우 3) out==2:
                if in>0: ---> 8자 그래프 갯수 +=1
                else: ---> 이 노드가 시작 정점
        경우 4) out>2: ---> 이 노드가 시작 정점
        */
        int[] answer = new int[4]; // 절점, 도넛, 막대, 8자
        
        for(Map.Entry<Integer, int[]> entry: map.entrySet()) {
            switch(entry.getValue()[1]) {
                case 0:
                    answer[2]++;
                    break;
                case 1:
                    break;
                case 2:
                    if(entry.getValue()[0]>0) answer[3]++;
                    else answer[0] = entry.getKey();
                    break;
                default:
                    answer[0] = entry.getKey();
            }
        }
        
        // 도넛 == 절점의 out에서 막대, 8자를 빼고 남은 그래프
        answer[1] = map.get(answer[0])[1] - answer[2] - answer[3];
        
        return answer;
    }
}