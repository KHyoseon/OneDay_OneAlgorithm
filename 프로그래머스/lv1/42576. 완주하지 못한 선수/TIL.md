다른 사람의 풀이를 보니 이런 코드가 있었다.


```
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}```


여기서 새로 배운 부분은 **hm.put(player, hm.getOrDefault(player, 0) + 1)** 부분이었다.

## getOrDefault(Object Key, Object defaultValue)
=> 찾는 key가 존재한다면 찾는 key의 value를 반환하고 없거나 null이면 default 값을 반환한다.


나는 여태껏
```
if(!map.containsKey(pt)){
    map.put(pt, 1);
    continue;
}
map.put(pt, map.get(pt) + 1);```
            
처럼 풀었는데 이미 Collections 라이브러리에 동일한 함수가 내장되어 있었다.
