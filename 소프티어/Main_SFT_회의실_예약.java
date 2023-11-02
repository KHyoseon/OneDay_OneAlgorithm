import java.io.*;
import java.util.*;

public class Main {
  
    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer stz = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(stz.nextToken());
      int M = Integer.parseInt(stz.nextToken());

      // 회의실 init
      Map<String, PriorityQueue<int[]>> rooms = new TreeMap<>();
      for(int i=0; i<N; i++) {
        rooms.put(br.readLine(), new PriorityQueue<>((o1, o2) -> o1[0]-o2[0]));
      }

      // 회의 표시
      for(int i=0; i<M; i++) {
        stz = new StringTokenizer(br.readLine());
        
        String room = stz.nextToken();
        int st = Integer.parseInt(stz.nextToken());
        int et = Integer.parseInt(stz.nextToken());

        rooms.get(room).add(new int[]{st, et});
      }

      StringBuilder sb = new StringBuilder();
      
      for(Map.Entry<String, PriorityQueue<int[]>> entry : rooms.entrySet()) {
        String room = entry.getKey();
        PriorityQueue<int[]> pq = entry.getValue();

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(9);
        
        while(!pq.isEmpty()) {
          int[] temp = pq.poll();
          if (dq.getLast() == temp[0]) {
            dq.pollLast();
          } else {
            dq.add(temp[0]);
          }
          dq.add(temp[1]);
        }
        
        if (dq.getLast() == 18) {
          dq.pollLast();
        } else {
          dq.add(18);
        }

        sb.append("Room ").append(room).append(":\n");
        
        if(dq.size() == 0) {
          sb.append("Not available\n");
        }
        else {
          sb.append(dq.size()/2).append(" available:\n");
          
          while(!dq.isEmpty()) {
            String s = dq.poll() + "";
            String e = dq.poll() + "";
            sb.append(s.equals("9")?"09":s).append("-").append(e).append("\n");
          }
        }
        
        sb.append("-----\n");
      }

      sb.setLength(sb.length() - 6);
      System.out.println(sb);
    }
}
