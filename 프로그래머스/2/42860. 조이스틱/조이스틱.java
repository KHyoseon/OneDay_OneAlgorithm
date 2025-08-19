import java.util.*;

class Solution {
    public int solution(String nm) {
        int INF = 21;
        char[] name = nm.toCharArray();
        int LENGTH = name.length;

        int SUM = 0;
        int MAX = 0, S = INF, L = INF;
        int max = 0, s = INF, l = INF;
        
        // A 연속최장구간 찾기, 상하 조작 횟수
        for(int i=0; i<LENGTH; i++) {
            // A면
            if(name[i] == 'A') {
                // 시작이면 s 기록
                if(s == INF)
                    l = s = i;
                // 구간 늘림
                l++;
                
                // 이게 마지막 구간이면 이전 값 갱신 및 초기화
                if(l < LENGTH && name[l] != 'A') {
                    if(MAX < l-s) {
                        MAX = l-s;
                        S = s;
                        L = l;
                    }
                    s = l = INF;
                }
            }
            
            SUM += Math.min(Math.abs(name[i] - 'A'), Math.abs('Z' - name[i]) + 1);
        }

        // 좌우 조작
        
        // 1. 중간에 꺾음
        int forward = S==0? 0: S-1;    // 0에서 A최장구간 전까지
        int backward = Math.abs(LENGTH - 1 - L) + 1;  // 0에서 LENGTH, LENGTH에서 최장구간 끝까지
        int comp1 = forward + backward + Math.min(forward, backward);

        // 2. 그냥 처음부터 (A가 아닌)끝까지 다 이동
        int comp2 = LENGTH - 1;
        for(int i=LENGTH-1; i>0 && name[i]=='A'; i--)
            comp2--;
        
        // System.out.println(nm.replace('A',  '-'));
        // System.out.printf(">> 그냥 쭉: %d, 중간에 꺾음: (0~%d, %d~%d+1)=> %d\n\n", comp2, S, L, LENGTH-1, comp1);
        return SUM + Math.min(comp1, comp2);
    }
}