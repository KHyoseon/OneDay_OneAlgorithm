import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int INNING, MAX = 0;
    static int[][] players;
    static int[] order = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        INNING = Integer.parseInt(br.readLine());
        players = new int[9][INNING];

        StringTokenizer st;
        for(int i = 0; i< INNING; i++) {
            st = new StringTokenizer(br.readLine());
            for(int p=0; p<9; p++) {
                players[p][i] = Integer.parseInt(st.nextToken());
            }
        }
        order[3] = 0;

        comb(0, 0);

        System.out.println(MAX);
    }

    private static void comb(int cur, int flag) {
        if(cur == 9) {
            MAX = Math.max(MAX, simulate());
            return;
        }

        if (cur==3){
            comb(4, flag);
        }
        else {
            for (int i = 1; i < 9; i++) {
                if ((flag & 1 << i) != 0) continue;
                order[cur] = i;
                comb(cur + 1, flag | 1 << i);
            }
        }
    }

    static int inning, score, out;
    static int[] base = new int[3];

    private static int simulate() {
        inning = score = out = 0;
        base[0] = base[1] = base[2] = 0;

        boolean outted = false;
        int o = 0;
        while(inning < INNING) {
            while (!outted) {
                outted = attack(players[order[(o++) % 9]][inning]);
            }
            inning++;
            out = 0;
            base[0] = base[1] = base[2] = 0;
            outted = false;
        }

        return score;
    }

    private static boolean attack(int play) {
        switch (play) {
            case 0:
                out++;
                if(out >= 3)    return true;
                break;
            case 1:
                score += base[2];
                base[2] = base[1];
                base[1] = base[0];
                base[0] = 1;
                break;
            case 2:
                score += base[2];
                score += base[1];
                base[2] = base[0];
                base[1] = 1;
                base[0] = 0;
                break;
            case 3:
                score += base[2];
                score += base[1];
                score += base[0];
                base[1] = base[0] = 0;
                base[2] = 1;
                break;
            case 4:
                score += base[2];
                score += base[1];
                score += base[0];
                score++;
                base[2] = base[1] = base[0] = 0;
                break;
        }
        return false;
    }

}