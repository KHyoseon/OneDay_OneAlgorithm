import java.util.*;

class Solution {
    Set<String> kind;
    Map<String, Integer> map;
    public int[] solution(String[] gems) {
        kind = new HashSet<>();
        map = new HashMap<>();

        for(String gem: gems){
            kind.add(gem);
        }

        int min = kind.size();
        int max = gems.length;

        if(min == 1){
            return new int[]{1, 1};
        }

        int minHead = 0, minTail = max;
        int head = 0, tail = 1;
        map.put(gems[head], 1);

        for(int l=gems.length; head<l-min && tail<l; tail++){
            if(map.size() == min){
                if (max > tail-head){
                    max = tail-head;
                    minHead = head;
                    minTail = tail;
                }
                map.remove(gems[head]);
                head++;
            }

            while(true){
                if(map.get(gems[head]) == 1) break;
                map.replace(gems[head], map.get(gems[head]) - 1);
                head++;
            }

            if(map.containsKey(gems[tail])) {
                map.replace(gems[tail], map.get(gems[tail]) + 1);
            }
            else
                map.put(gems[tail], 1);
        }

        if (map.size() == min && max > tail-head){
            minHead = head;
            minTail = tail;
        }

        return new int[]{minHead+1, minTail};
    }
}