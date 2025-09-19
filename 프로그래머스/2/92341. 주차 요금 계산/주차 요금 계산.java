import java.util.*;

class Solution {
	public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Record> map = new TreeMap<>();
        
        for(String record: records) {
        	String[] temp = record.split(" ");
            // "05:34 5961 IN"
            if(temp[2].equals("IN")) {
                map.put(temp[1], new Record(temp[0]));
            } else {
                Record rec = map.get(temp[1]);
                rec.setTime(temp[0], temp[2]);
                rec.updateFee(fees);
            }
        }
        
        for(String key: map.keySet()) {
            Record rec = map.get(key);
            if(rec.inTime == -1 && rec.outTime == -1) continue;
            System.out.println(""+key);
            rec.setTime("23:59", "OUT");
            System.out.println(">> " + rec.inTime+", " + rec.outTime);
            rec.updateFee(fees);
        }
        	
        int[] answer = new int[map.size()];
        int i=0;
        for(String key: map.keySet())
            answer[i++] = map.get(key).fee;
        
        return answer;
    }
	
	class Record {
		int inTime, outTime;
		int time, fee;
		
		public Record(String time) {
            this.inTime = toMinutes(time);
        }
		
		public void setTime(String time, String opt) {
			if(opt.equals("IN")) {
				this.inTime = toMinutes(time);
			} else {
				this.outTime = toMinutes(time);
			}
		}
        
        private void updateFee(int[] fees) {
        	// fees: 기본 시간, 기본 요금, 단위 시간, 단위 요금
            int time = this.outTime - this.inTime;
            int overTime = Math.max(time - fees[0], 0);
            this.fee += fees[1] + (overTime==0? 0:
                            fees[3] * (int) Math.ceil(overTime / fees[2]));
            
            this.inTime = this.outTime = -1;
        }
        
        private int toMinutes(String time) {
            String[] str = time.split(":");
            return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        }
	}
}