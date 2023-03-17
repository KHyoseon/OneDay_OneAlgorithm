import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Round[] round;
    static Set<Integer> answer = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        round = new Round[N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            round[i] = new Round(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        guess(0, 0, 0);
        System.out.println(answer.size());
    }

    private static void guess(int ans, int index, int selected) {
        if(index == 3) {
            for(Round r: round) {
                if(!match(r, ans)){
                    return;
                }
            }
            answer.add(ans);
            return;
        }

        for(int i=1; i<10; i++) {
            if((selected & 1<<i) != 0) continue;
            guess(ans * 10 + i, index + 1, selected | (1 << i));
        }
    }

    private static boolean match(Round rnd, int ans) {
        int strike = rnd.strike, ball = rnd.ball, expect = rnd.expect;
        int a=0, r=0;

        while (ans > 0 && strike >= 0) {
            if(expect % 10 == ans % 10) --strike;
            else {
                a |= (1 << ans % 10);
                r |= (1 << expect % 10);
            }

            expect /= 10;
            ans /= 10;
        }

        if(strike != 0) return false;

        int gyo = a & r;
        for(int i=1; i<10 && ball>=0; i++) {
            if((gyo & (1<<i)) != 0) --ball;
        }

        if(ball != 0) return false;
        return true;
    }

    static class Round {
        int expect;
        int strike, ball;

        public Round(int expect, int strike, int ball) {
            this.expect = expect;
            this.strike = strike;
            this.ball = ball;
        }
    }
}