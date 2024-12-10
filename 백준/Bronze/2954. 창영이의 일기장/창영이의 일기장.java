import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        LinkedList<Character> temp = new LinkedList<>();

        for(char c: input.toCharArray()) {
            temp.add(c);
        }

        int l = temp.size();
        for(int i=0; i<l-2; i++) {
            if(temp.get(i) == 'a'
                || temp.get(i) == 'e'
                || temp.get(i) == 'i'
                || temp.get(i) == 'o'
                || temp.get(i) == 'u')
            {
                if (temp.get(i + 1) != 'p') continue;
                if (temp.get(i).equals(temp.get(i + 2))) {
                    temp.remove(i + 1);
                    temp.remove(i + 1);
                }
            }
            l = temp.size();
        }

        StringBuilder sb = new StringBuilder();

        for(char c: temp) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}