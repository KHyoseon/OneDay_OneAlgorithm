import java.util.HashMap;

class Solution {
    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String pt: participant){
            if(!map.containsKey(pt)){
                map.put(pt, 1);
                continue;
            }
            map.put(pt, map.get(pt) + 1);
        }

        for(String com: completion){
            if(map.get(com) == 1){
                map.remove(com);
                continue;
            }
            map.put(com, map.get(com) - 1);
        }

        String answer = "";

        for(String key: map.keySet()){
            answer = key;
        }

        return answer;
    }
}