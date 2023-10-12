package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_SWEA_원재의_메모리_복구하기 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for(int t=0; t<TC; t++) {
            sb.append("#").append(t+1).append(" ");

            String target = br.readLine();

            String copy = "0" + target;
            int cnt = 0, l = copy.length();

            while(copy.contains("10")) {
                ++cnt;
                copy = copy.substring(Math.min(copy.indexOf("10") + 2, l));
            }

            copy = "0" + target;
            while(copy.contains("01")) {
                ++cnt;
                copy = copy.substring(Math.min(copy.indexOf("01") + 2, l));
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}