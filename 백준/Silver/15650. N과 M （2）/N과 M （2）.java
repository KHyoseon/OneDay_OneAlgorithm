import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Comb(1, N, M, new StringBuilder());

        for(String answer: list)
            sb.append(answer).append("\n");

        System.out.print(sb);
    }

    static String content;
    static void Comb(int start, int n, int r, StringBuilder builder){
        if(r <= 0) {
            content = builder.toString();
            if(!list.contains(content))
                list.add(content);
            return;
        }
        for(int i=start; i<=n; i++){
            builder.append(i).append(" ");
            Comb(i+1, n, r-1, builder);
            builder.setLength(builder.length() - 2);
            Comb(i+1, n, r, builder);
        }
    }
}