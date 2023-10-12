package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_SWEA_암호문1 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = 10;

        LinkedList<String> list = new LinkedList<>();
        List<String> lst = new ArrayList<>();

        while(TC > 0) {
            sb.append("#").append(11 - TC).append(" ");
            list.clear();

            // 암호문 길이
            int N = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");

            for(String input: inputs) {
                list.add(input);
            }

            // 명령어 개수
            int C = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int index, cnt;
            while(st.hasMoreTokens()) {
                switch (st.nextToken()) {
                    case "I":
                        index = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());

                        lst.clear();
                        for(int i=0; i<cnt; i++) {
                            lst.add(st.nextToken());
                        }

                        list.addAll(index, lst);
                        break;

                    case "D":
                        index = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());

                        for(int i=0; i<cnt && list.size() > index+1; i++) {
                            list.remove(index);
                        }

                        break;

                    case "A":
                        cnt = Integer.parseInt(st.nextToken());

                        for(int i=0; i<cnt; i++) {
                            list.add(st.nextToken());
                        }

                        break;
                }
            }

            for(int i=0; i<10; i++) {
                sb.append(list.poll()).append(" ");
            }
            sb.append("\n");

            TC--;
        }

        System.out.println(sb);
    }
}
