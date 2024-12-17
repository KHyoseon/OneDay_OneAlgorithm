import java.util.*;

class Solution {
    int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        
        for(int r=0; r<rows; r++) {
            for(int c=0; c<columns; c++) {
                map[r][c] = r*columns + c+1;
            }
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        for(int[] query: queries) {
            answer.add(turn(query[0]-1, query[1]-1, query[2]-1, query[3]-1));
            // print();
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /*public void print() {
        for(int i=0, r=map.length; i<r; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }*/
    
    public int turn(int x1, int y1, int x2, int y2) {
        int MIN = Integer.MAX_VALUE;
        
        int temp = map[x1][y2];
        MIN = Math.min(MIN, temp);
        
        // 위
        for(int c=y2-1; c>=y1; c--) {
            MIN = Math.min(MIN, map[x1][c]);
            map[x1][c+1] = map[x1][c];
        }
        
        // 좌
        for(int r=x1+1; r<=x2; r++) {
            MIN = Math.min(MIN, map[r][y1]);
            map[r-1][y1] = map[r][y1];
        }
        
        // 아래
        for(int c=y1+1; c<=y2; c++) {
            MIN = Math.min(MIN, map[x2][c]);
            map[x2][c-1] = map[x2][c];
        }
        
        // 우
        for(int r=x2-1; r>=x1; r--) {
            MIN = Math.min(MIN, map[r][y2]);
            map[r+1][y2] = map[r][y2];
        }
        
        map[x1+1][y2] = temp;
        
        return MIN;
    }
}