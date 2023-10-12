package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_SWEA_비밀번호 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = 10;
        while(TC > 0) {
            sb.append("#").append(11 - TC).append(" ");

            String[] input = br.readLine().split(" ");
            String target = input[1];

            String[] nums = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
            boolean done = false;
            while(!done) {
                done = true;
                for (int i = 0; i < 10; i++) {
                    while (target.contains(nums[i])) {
                        target = target.replace(nums[i], "");
                        done = false;
                    }
                }
            }
            sb.append(target).append("\n");
            TC--;
        }

        System.out.println(sb);
    }
}