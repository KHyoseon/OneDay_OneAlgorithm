import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열_폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        int B = bomb.length;

        Stack<Character> str = new Stack<>();
        Stack<Integer> ref = new Stack<>();

        str.add(' ');
        ref.add(0);

        for(char c: input){
            str.add(c);
            if(c == bomb[ref.peek()])
                ref.add(ref.peek() + 1);
            else if (c == bomb[0])
                ref.add(1);
            else
                ref.add(0);

            if(ref.peek() == B){
                for(int b=0; b<B; b++){
                    str.pop();
                    ref.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c: str){
            sb.append(c);
        }

        String out = sb.toString().trim();
        System.out.println("".equals(out)?"FRULA":out);
    }

    // str Stack 하나만 사용해서 하나씩 스택에 넣을 때 마다
    // bomb 사이즈보다 크면 무조건 뒤에서부터 비교
    /*public static void func() {
        for (int i = 0; i < tot_size; i++) {
            st.add(arr[i]);
            if (bomb_size <= st.size()) {
                boolean flag = true;
                for (int j = 0; j < bomb_size; j++) {
                    if (bomb[bomb_size - 1 - j] != st.elementAt(st.size() - j - 1)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < bomb_size; j++) st.pop();
                }
            }
        }
    }*/
}
