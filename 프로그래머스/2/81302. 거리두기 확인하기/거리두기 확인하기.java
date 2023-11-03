import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int T = places.length;
        int[] answer = new int[T];
        
        ArrayList<int[]> parts = new ArrayList<>();
        
        int a=0;
        for(String[] place: places) {
            // 참가자 위치 저장
            for(int i=0; i<5; i++) {
                int c = -1;
                while(true) {
                    c = place[i].indexOf("P", c+1);
                    if(c == -1) break;
                    parts.add(new int[]{i, c});
                }
            }
            
            // for(int[] p: parts) {
            //     System.out.println(Arrays.toString(p));
            // }
            // System.out.println("---");
            
            // 계산
            answer[a++] = calcResult(place, parts);
            // System.out.println(answer[a-1]);
            parts.clear();
        }
        
        return answer;
    }
    
    public int getDist(int[] m, int[] y) {
        return Math.abs(m[0]-y[0]) + Math.abs(m[1]-y[1]);
    }
    
    public int calcResult(String[] place, ArrayList<int[]> parts) {
        // 참가자끼리 거리 비교
        boolean[] visited = new boolean[parts.size()];
        int l=parts.size();
        
        for(int i=0; i<l; i++) {
            int[] me = parts.get(i);
            
            for(int j=0; j<l; j++) {
                int[] you = parts.get(j);
                
                if(me==you || visited[j] || you[1]-me[1] > 2) continue;
                
                int dist = getDist(me, you);
                // 거리가 2 초과면 통과
                if(dist > 2) continue;
                // 거리가 1 이하면 무조건 실패
                if(dist <= 1){
                    // System.out.println(Arrays.toString(me) + " & " + Arrays.toString(you) + "거리 1이하");
                    return 0;
                }
                
                // 직선 위에 있다
                if(me[0]==you[0] || me[1]==you[1]) {
                    if(place[(me[0]+you[0])/2].charAt((me[1]+you[1])/2) == 'O') {
                        // System.out.println(Arrays.toString(me) + " & " + Arrays.toString(you) + "가운데 파티션 없음");
                        return 0;
                    }
                }
                // 대각선 위에 있다
                else {
                    if(place[me[0]].charAt(you[1]) == 'O' ||
                      place[you[0]].charAt(me[1]) == 'O') {
                        // System.out.println(Arrays.toString(me) + " & " + Arrays.toString(you) + "양옆에 파티션 없음");
                        return 0;
                    }
                }
            }
            visited[i] = true;
        }
        // System.out.println("거리두기 ok");
        return 1;
    }
}