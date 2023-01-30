import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String[] part: clothes) {
            map.put(part[1], map.getOrDefault(part[1], 0) + 1);
        }

        int sum = 1;
        for(String part: map.keySet()){
            sum *= map.get(part) + 1;
        }

        return sum - 1;
    }
}