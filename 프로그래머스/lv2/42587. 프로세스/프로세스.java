import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Process> queue = new LinkedList<>();
        
        int order = 0;
        for(int priority: priorities) {
            pq.add(priority);
            queue.add(new Process(order++, priority));
        }
        
        int answer = 0;
        while(true) {
            Process pop = queue.poll();
            int MAX = pq.poll();
            
            if(pop.priority != MAX) {
                queue.add(pop);
                pq.add(MAX);
                continue;
            }
            
            System.out.println(MAX + " VS " + pop.index);
            answer++;
            if(pop.index == location) break;
        }
        
        return answer;
    }
}

class Process {
    int index, priority;
    public Process(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }
}