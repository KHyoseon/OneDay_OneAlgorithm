class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int length = convertToInt(video_len);
        int now = convertToInt(pos);
        int op_s = convertToInt(op_start);
        int op_e = convertToInt(op_end);
        
        for(String command: commands) {
            
            if(op_s <= now && now <= op_e)
                now = op_e;
            
            switch(command) {
                case "prev":
                    now = Math.max(0, now - 10);
                    break;
                case "next":
                    now = Math.min(length, now + 10);
                    break;
            }
        }
        
        if(op_s <= now && now <= op_e)
            now = op_e;
            
        return convertToString(now);
    }
    
    public int convertToInt(String time) {
        int timeInt = 0;
        String[] split = time.split(":");
        
        timeInt += (Integer.parseInt(split[0])) * 60;
        timeInt += Integer.parseInt(split[1]);
        
        return timeInt;
    }
    
    public String convertToString(int time) {
        String ret = String.format("%2d:%2d", time/60, time%60);
        ret = ret.replace(" ", "0");
        return ret;
    }
}