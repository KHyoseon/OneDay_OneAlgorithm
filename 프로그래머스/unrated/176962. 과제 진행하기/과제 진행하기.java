import java.util.*;

class Solution {
    public ArrayList<String> solution(String[][] plans) {
        int N = plans.length;
        ArrayList<String> answer = new ArrayList<>();
        
        Work[] works = new Work[N];
        
        for(int i=0; i<N; i++) {
            String[] time = plans[i][1].split(":");
            works[i] = new Work(plans[i][0]
                        , Integer.parseInt(time[0]) * 60
                                + Integer.parseInt(time[1])
                       , Integer.parseInt(plans[i][2]));
        }
        
        Arrays.sort(works, (o1, o2) -> o1.startTime==o2.startTime? o1.playtime-o2.playtime: o1.startTime-o2.startTime);
        
        Stack<Work> remain = new Stack<>();
        int curTime = works[0].startTime;
        int next = 1;
        Work curWork = works[0];
        
        // System.out.println("시작 > "+ works[0].name + ", curTime: " + curTime);
        
        while(next<N) {
            int min = curWork.playtime - (works[next].startTime - curTime);
            // 남은 과제를 다 끝낼 수 있다
            if(min <= 0){
                // 현재 작업 중인거 끝냄
                curTime += curWork.playtime;
                curWork.playtime = 0;
                answer.add(curWork.name);
                // System.out.println("끝 > "+curWork.name + ", curTime: "+curTime);
                
                // 다음 작업 바로 시작
                if(min == 0) {
                    curWork = works[next];
                    next++;
                    // System.out.println("시작 > "+ curWork.name + ", curTime: " + curTime);
                } else { // 이전에 중단한 작업 이어서 함
                    if(!remain.isEmpty()){
                        curWork = remain.pop();
                        // System.out.println("재개 > "+ curWork.name+ "(" + curWork.playtime + "), curTime: " + curTime);
                    }
                    else {
                        curTime = works[next].startTime;
                        curWork = works[next];
                        next++;
                        // System.out.println("시작 > "+ curWork.name + ", curTime: " + curTime);
                    }
                }
            } // 끊고 다음 과제를 할 시간이라면
            else {
                curWork.playtime -= (works[next].startTime - curTime);
                curTime += (works[next].startTime - curTime);
                remain.add(curWork);
                // System.out.println("중단 > "+ curWork.name+ "(" + curWork.playtime + "),  curTime: " + curTime);
                curWork = works[next];
                next++;
                // System.out.println("시작 > "+ curWork.name + ", curTime: " + curTime);
            }
        }
        
        if(curWork.playtime != 0) remain.add(curWork);
        
        Work re;
        while(!remain.isEmpty()) {
            re = remain.pop();
            answer.add(re.name);
            // curTime += re.playtime;
            // System.out.println("끝 > "+re.name + ", curTime: "+curTime);
        }
        
        return answer;
    }
                    
    class Work {
        String name;
        int startTime;
        int playtime;
        
        public Work(String name, int startTime, int playtime) {
            this.name = name;
            this.startTime = startTime;
            this.playtime = playtime;
        }
    }
}