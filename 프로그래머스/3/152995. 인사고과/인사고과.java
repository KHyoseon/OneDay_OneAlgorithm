import java.util.*;

class Solution {
    class Score {
        int s1, s0, sum;
        public Score(int s0, int s1) {
            this.s0 = s0;
            this.s1 = s1;
            this.sum = s0+s1;
        }
        
        public boolean lessThan(Score o) {
            if(this.s1 < o.s1 && this.s0 < o.s0) return true;
            return false;
        }
    }
    
    public int solution(int[][] scores) {
        ArrayList<Score> sum = new ArrayList<>();

        for(int[] sc: scores) {
            sum.add(new Score(sc[0], sc[1]));
        }
        Score wh = sum.get(0);
        ArrayList<Score> s0 = new ArrayList<>(sum);
        ArrayList<Score> s1 = new ArrayList<>(sum);

        sum.sort((o1, o2) -> o2.sum - o1.sum);
        s0.sort((o1, o2) -> o2.s0 - o1.s0);
        s1.sort((o1, o2) -> o2.s1 - o1.s1);

        int prize = 1, cnt = 0;
        int prev = sum.get(0).sum;

        for(int i=0, l=scores.length-1; i<l; i++, cnt++) {
            Score cur = sum.get(i);
            // i번째 사원 때문에 인센 못 받음
            if(wh.lessThan(cur)) return -1;

            // i번째 사원 인센 받기 가능?
            if(!incentive(cur, s0, s1)) {
                cnt--;
                continue;
            }

            // i번째 사원 점수
            // 동점이 아님 -> 현재 등수 조정
            if(prev != cur.sum) {
                prev = cur.sum;
                prize = cnt + 1;
            }

            if(cur.equals(wh)) break;
        }

        return prize;
    }

    private boolean incentive(Score cur, ArrayList<Score> s0, ArrayList<Score> s1) {
        int idx0 = s0.indexOf(cur);
        int idx1 = s1.indexOf(cur);

        if(idx0 < idx1) {
            for (int i = 0; i < idx0; i++) {
                if(cur.lessThan(s0.get(i))) return false;
            }
        } else {
            for (int i = 0; i < idx1; i++) {
                if(cur.lessThan(s1.get(i))) return false;
                /*Score t = s1.get(i);
                if(t.s1 <= cur.s1) continue;

                int ti = s0.indexOf(t);
                if(ti < idx0 && t.s0 > cur.s0)
                    return false;*/
            }
        }

        return true;
    }
}