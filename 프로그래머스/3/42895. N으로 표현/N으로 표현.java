import java.util.*;

class Solution {
    static ArrayList<Set<Integer>> list = new ArrayList<>(9);
    public int solution(int N, int number) {
        if(N == number) return 1;
        if(Integer.parseInt(N+""+N) == number) return 2;
        
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        for(int i=0; i<9; i++)
            list.add(new HashSet<>());
        // N 1개 사용
        list.get(1).add(Integer.parseInt(sb.toString()));
        // N 2개 사용
        sb.append(N);
        list.get(2).add(Integer.parseInt(sb.toString()));
        list.get(2).add(1);
        list.get(2).add(N + N);
        list.get(2).add(N * N);

        for(int use=3; use<9; use++) {
            sb.append(N);
            list.get(use).add(Integer.parseInt(sb.toString()));
            for(int i=1; i<=use/2; i++) {
                for(int p: list.get(i)) {
                    for(int pp: list.get(use-i)) {
                        list.get(use).add(p + pp);
                        list.get(use).add(p * pp);

                        int max = Math.max(p, pp);
                        int min = Math.min(p, pp);
                        if(max != min)
                            list.get(use).add(max - min);
                        if(max%min == 0)
                            list.get(use).add(max / min);
                    }
                }
            }
            if(list.get(use).contains(number))
                return use;
        }

        return -1;
    }
}