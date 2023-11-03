import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int N = Integer.parseInt(br.readLine());
      int[][] times = new int[N][2];

      StringTokenizer st;
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        times[i][0] = Integer.parseInt(st.nextToken());
        times[i][1] = Integer.parseInt(st.nextToken());
      }

      Arrays.sort(times, (o1, o2)->o1[0]==o2[0]? o1[1]-o2[1]: o1[0]-o2[0]);

      // 끝나는 시각, 예약 강의 개수
      int end = times[0][1];
      int count = 1;
      
      for(int i=1; i<N; i++) {
        if(end > times[i][1]) {
          end = times[i][1];
        } else if (end <= times[i][0]){
          end = times[i][1];
          count++;
        }
      }

      System.out.println(count);
    }
}
