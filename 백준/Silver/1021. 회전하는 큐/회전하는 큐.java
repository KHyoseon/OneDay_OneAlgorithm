import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i=0; i<N; i++){
            linkedList.add(i);
        }

        int cnt = 0, size = N;
        int idx;
        for(int find: arr) {
            while(linkedList.peek()!=find) {
                idx = linkedList.indexOf(find);
                if (idx < size - idx) {
                    linkedList.add(linkedList.poll());
                } else {
                    linkedList.add(0, linkedList.get(size - 1));
                    linkedList.removeLast();
                }
                cnt++;
            }

            linkedList.poll();
            size--;
        }

        System.out.println(cnt);
    }
}