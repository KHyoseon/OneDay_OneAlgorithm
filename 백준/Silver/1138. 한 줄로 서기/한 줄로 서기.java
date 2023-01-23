import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] tallerThanMe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tallerThanMe = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            tallerThanMe[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(N);
        for(int t=N-2; t>=0; t--) {
            int head = 0;
            int n = arrayList.size();
            for(head=0; head<n && tallerThanMe[t]>0; head++){
                if(arrayList.get(head)>t) tallerThanMe[t]--;
            }
            arrayList.add(head, t+1);
        }

        for(int list: arrayList)
            sb.append(list).append(" ");

        System.out.println(sb);
    }
}