package code;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_PG_120812_최빈값_구하기 {
    public static void main(String[] args) {
        int[] array = {1};
        System.out.println(new Solution_120812().solution(array));
    }
}

class Solution_120812 {
    public int solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i: array){
            if(!map.containsKey(i)){
                map.put(i, 1);
            } else {
                map.replace(i, map.get(i) + 1);
            }
        }

        PriorityQueue<Num> nums = new PriorityQueue<>();
        for(int key: map.keySet()){
            nums.add(new Num(key, map.get(key)));
        }
        if(nums.size() == 1)
            return nums.poll().number;

        Num max = nums.poll();
        Num snd = nums.poll();
        if (max.cnt == snd.cnt) return -1;
        else return max.number;
    }

    class Num implements Comparable<Num>{
        int number, cnt;

        public Num(int n, int c) {
            this.number = n;
            this.cnt = c;
        }

        @Override
        public int compareTo(Num o) {
            return o.cnt - this.cnt;
        }
    }

    int solution2(int[] array) {
        int maxCount = 0;
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int number : array){
            int count = map.getOrDefault(number, 0) + 1;
            if(count > maxCount){
                maxCount = count;
                answer = number;
            }
            else  if(count == maxCount){
                answer = -1;
            }
            map.put(number, count);
        }
        return answer;
    }
}