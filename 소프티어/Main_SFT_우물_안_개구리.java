import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      boolean[] visited = new boolean[N];
      int[] weights = new int[N];

      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        weights[i] = Integer.parseInt(st.nextToken());
      }

      HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

      for(int i=0; i<M; i++) {
        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken()) - 1;
        int p2 = Integer.parseInt(st.nextToken()) - 1;
        if(!hm.containsKey(p1)) hm.put(p1, new ArrayList<>());
        if(!hm.containsKey(p2)) hm.put(p2, new ArrayList<>());
        hm.get(p1).add(p2);
        hm.get(p2).add(p1);
      }

      int CNT = 0;
      for(Map.Entry<Integer, ArrayList<Integer>> entry: hm.entrySet()) {
        int me = entry.getKey();
        ArrayList<Integer> friends = entry.getValue();

        boolean defeat = false;

        for(int f: friends) {
          if(weights[me] <= weights[f]) {
            defeat = true;
            break;
          }
        }
        
        if(!defeat) {
          CNT++;
          for(int f: friends) {
            // System.out.println(me + " wins");
            visited[f] = true;
          }
        }
        
        visited[me] = true;
      }

      int i=0;
      for(boolean v: visited) {
        if(!v){
          CNT++;
          // System.out.println(i + " 관계없음");
        }
        i++;
      }

      System.out.println(CNT);
    }
}
