import java.util.*;

class Solution {
    ArrayList<Integer>[] possible;
    int[] cnt = new int[32001];

    public int solution(int N, int number) {
        if(N==number) return 1;

        Arrays.fill(cnt, 10);

        possible = new ArrayList[9];
        for (int i = 1; i < 9; i++) {
            possible[i] = new ArrayList<>();
        }
        
        int count = 1, temp = N;
        while(temp < 32001){
            cnt[temp] = count;
            possible[count++].add(temp);
            temp = temp*10 + N;
            if(temp==number) return count;
        }

        ArrayList<Integer> ret = calc(N, N);
        for(int n: ret) {
            if(n==number) return 2;
            if(cnt[n] > 2) {
                cnt[n] = 2;
                possible[2].add(n);
            }
        }

        for(int t=3; t<9; t++) {
            for (int i = 1; i < t/2 + 1; i++) {
                for(int numI: possible[i]){
                    for(int numJ: possible[t-i]){
                        ret = calc(numI, numJ);
                        for(int n: ret) {
                            if(n==number) return t;
                            if(cnt[n] > t) {
                                cnt[n] = t;
                                possible[t].add(n);
                            }
                        }
                    }
                }
            }
        }

        return cnt[number]>8? -1: cnt[number];
    }

    private ArrayList<Integer> calc(int p, int pp) {
        ArrayList<Integer> ret = new ArrayList<>();
        if(0 < p/pp) ret.add(p / pp);
        if(0 < pp/p) ret.add(pp / p);
        if(p+pp < 32001) ret.add(p + pp);
        if(0 < p-pp) ret.add(p - pp);
        if(0 < pp-p) ret.add(pp - p);
        if(p*pp < 32001) ret.add(p * pp);
        return ret;
    }
}