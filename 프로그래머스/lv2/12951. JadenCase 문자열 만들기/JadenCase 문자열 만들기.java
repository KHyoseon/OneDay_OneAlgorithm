class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        char[] str = s.toLowerCase().toCharArray();
        sb.append((str[0] + "").toUpperCase());

        for(int i=1; i< str.length; i++) {
            if(str[i] != ' ' && str[i-1] == ' ')
                sb.append((str[i] + "").toUpperCase());
            else
                sb.append(str[i]);
        }

        return sb.toString();
    }
}