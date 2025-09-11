import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int N = timetable.length;

        int[] crew = new int[N];
        for(int i=0; i<N; i++) {
            crew[i] = convertToInt(timetable[i]);
        }
        Arrays.sort(crew);

        int[] bus = new int[n];
        bus[0] = convertToInt("9:00");
        for(int i=1; i<n; i++) {
            bus[i] = bus[i - 1] + t;
        }

        int c = 0;
        int answer = 0;
        for(int b=0; b<n; b++) {
            int remain = m;
            for(; c<N && 0<remain; c++) {
                if(bus[b] < crew[c]) break;
                remain--;
            }
            if(remain == 0)
                answer = crew[c - 1] - 1;
            else answer = bus[b];
        }


        return convertToString(answer);
    }

    private String convertToString(Integer bus) {
        int minute = bus.intValue();
        String answer = "";

        if(minute/60 < 10)  answer = "0";
        answer += minute/60 + ":";

        if(minute%60 < 10) answer += "0";
        answer += minute%60 + "";

        return answer;
    }

    private int convertToInt(String s) {
        String[] parse = s.split(":");
        return 60 * Integer.parseInt(parse[0]) + Integer.parseInt(parse[1]);
    }
}