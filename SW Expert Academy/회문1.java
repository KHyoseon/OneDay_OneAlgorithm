package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_SW_회문1 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T;
        T = Integer.parseInt(br.readLine());

        int N, cnt;
        char[][] map = new char[8][8];

        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            cnt = 0;

            // 맵 생성
            for(int i=0; i<8; i++) {
                map[i] = br.readLine().toCharArray();
            }

            for(int row=0; row<8; row++) {
                for(int col=0; col<=8-N; col++) {
                    boolean right = true;
                    for(int j=0; j<N/2; j++) {
                        if(map[row][col+j] != map[row][col+N-1-j]){
                            right = false;
                            break;
                        }
                    }
                    if(right) cnt++;
                }
            }
            for(int col=0; col<8; col++) {
                for(int row=0; row<=8-N; row++) {
                    boolean right = true;
                    for(int j=0; j<N/2; j++) {
                        if(map[row+j][col] != map[row+N-1-j][col]){
                            right = false;
                            break;
                        }
                    }
                    if(right) cnt++;
                }
            }
            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
