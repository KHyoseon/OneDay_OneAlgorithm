import java.util.*;
import java.lang.StringBuilder;

class Solution {
    int[] left, right;
    
    public String solution(int[] numbers, String hand) {
        int[][] pad = new int[12][2];
        
        pad[0] = new int[]{3, 1};
        pad[10] = new int[]{3, 0};
        pad[11] = new int[]{3, 2};
        
        int num=1;
        for(int r=0; r<3; r++) {
            for(int c=0; c<3; c++) {
                pad[num++] = new int[]{r, c};
            }
        }
        
        left = pad[10];
        right = pad[11];
        
        StringBuilder sb = new StringBuilder();
        
        for(int number: numbers) {
            if(number%3 == 1){
                left = pad[number];
                sb.append("L");
            } else if(number!=0 && number%3 == 0) {
                right = pad[number];
                sb.append("R");
            } else {
                int ret = dist(pad[number]);
                if(ret < 0){
                    left = pad[number];
                    sb.append("L");
                } else if(ret > 0) {
                    right = pad[number];
                    sb.append("R");
                } else {
                    if(hand.equals("left")) {
                        left = pad[number];
                        sb.append("L");
                    } else {
                        right = pad[number];
                        sb.append("R");
                    }
                }
            }
        }
        
        return sb.toString();
    }

    public int dist(int[] pos) {
        int l = Math.abs(pos[0] - left[0]) + Math.abs(pos[1] - left[1]);
        int r = Math.abs(pos[0] - right[0]) + Math.abs(pos[1] - right[1]);
        return l-r;
    }
}