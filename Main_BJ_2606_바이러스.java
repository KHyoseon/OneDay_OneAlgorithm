import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_2606_바이러스 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        Arrays.fill(parent, -1);

        String[] input;
        int a, b;
        for(int i=0; i<P; i++){
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            // 양방향으로 다 넣어줘야함
            if(a>b){
                int tmp = a;
                a = b;
                b = tmp;
            }
            parent[b] = findJosang(a);
        }

        int cnt = 0;
        for(int i=2; i<N+1; i++){
            if(findJosang(i)==1)    ++cnt;
        }

        System.out.println(cnt);
    }

    private static int findJosang(int a){
        int next=a;
        for(; parent[next]!=-1; next=parent[next]);
        return next;
    }
}