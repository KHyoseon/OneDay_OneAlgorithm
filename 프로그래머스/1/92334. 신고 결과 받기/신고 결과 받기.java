import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> user = new HashMap<>();
        
        int idx = 0;
        for(String id: id_list) {
            user.put(id, idx++);
        }
        
        for(String rp: report) {
            String[] split = rp.split(" ");
            if(!map.containsKey(split[1]))
                map.put(split[1], new HashSet<>());
            map.get(split[1]).add(split[0]);
        }
        
        int[] answer = new int[id_list.length];
        
        for(String id: map.keySet()) {
            if(map.get(id).size() < k) continue;
            
            for(String id2: map.get(id)) {
                answer[user.get(id2)]++;
            }
        }
        
        return answer;
    }
}