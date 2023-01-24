import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
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
}