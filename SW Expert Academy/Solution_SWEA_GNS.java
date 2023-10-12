package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_SW_GNS {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        /*
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>((Comparator.comparingInt(map::get)));

        map.put("ZRO", 0);
        map.put("ONE", 1);
        map.put("TWO", 2);
        map.put("THR", 3);
        map.put("FOR", 4);
        map.put("FIV", 5);
        map.put("SIX", 6);
        map.put("SVN", 7);
        map.put("EGT", 8);
        map.put("NIN", 9);

        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append("\n");
            br.readLine();
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                queue.add(st.nextToken());
            }

            while(!queue.isEmpty()) {
                sb.append(queue.poll()).append(" ");
            }
            sb.append("\n");
        }*/

        String[] str = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
        int[] cnt = new int[10];

        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append("\n");

            br.readLine();
            st = new StringTokenizer(br.readLine());

            Arrays.fill(cnt, 0);
            while (st.hasMoreTokens()) {
                switch (st.nextToken()) {
                    case "ZRO":
                        cnt[0]++; break;
                    case "ONE":
                        cnt[1]++; break;
                    case "TWO":
                        cnt[2]++; break;
                    case "THR":
                        cnt[3]++; break;
                    case "FOR":
                        cnt[4]++; break;
                    case "FIV":
                        cnt[5]++; break;
                    case "SIX":
                        cnt[6]++; break;
                    case "SVN":
                        cnt[7]++; break;
                    case "EGT":
                        cnt[8]++; break;
                    case "NIN":
                        cnt[9]++; break;
                }
            }

            for(int i=0; i<10; i++) {
                for(int j=0; j<cnt[i]; j++) {
                    sb.append(str[i]).append(" ");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
