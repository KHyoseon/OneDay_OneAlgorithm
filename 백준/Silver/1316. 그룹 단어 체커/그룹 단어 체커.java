import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] inputs = new String[N];

        for(int i=0; i<N; i++) {
            inputs[i] = br.readLine();
        }

        int cnt = 0;
        for(String str: inputs) {
            selected = new boolean[26];
            if(check(str.toCharArray())) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean check(char[] letter) {
        int l = letter.length;
        for(int flag=0; flag<l; flag++) {
            int now = flag;
            if(selected[letter[now] - 'a']) return false;
            for(; flag+1<l && letter[now]==letter[flag+1]; flag++);
            selected[letter[flag]-'a'] = true;
        }
        return true;
    }
}