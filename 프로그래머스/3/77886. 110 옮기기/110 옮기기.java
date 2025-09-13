class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        int i=0;

        for(String str: s) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            
            for(char c: str.toCharArray()) {
                sb.append(c);
                if(sb.length() < 3) continue;
                int l = sb.length();
                if(sb.charAt(l-3) == '1' && sb.charAt(l-2) == '1' && sb.charAt(l-1) == '0') {
                    sb.setLength(l - 3);
                    cnt++;
                }
            }
            
            StringBuilder str110 = new StringBuilder();
            while(cnt > 0) {
                str110.append("110");
                --cnt;
            }

            int last0 = sb.lastIndexOf("0");
            sb.insert(last0 + 1, str110);
            answer[i++] = sb.toString();
        }
        return answer;
    }
}