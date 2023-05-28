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

        if (cur == 3){
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
    static int first, second, third;
    private static int simulate() {
        inning = score = out = 0;
        first = second = third = 0;

        boolean outted = false;
        int o = 0;
        while(inning < INNING) {
            while (!outted) {
                outted = attack(players[order[(o++) % 9]][inning]);
            }
            inning++;
            out = 0;
            first = second = third = 0;
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
                anta();
                break;
            case 2:
                tworuta();
                break;
            case 3:
                threeruta();
                break;
            case 4:
                homerun();
                break;
        }
        return false;
    }

    private static void homerun() {
        score += third;
        score += second;
        score += first;
        score++;
        third = second = first = 0;
    }

    private static void threeruta() {
        score += third;
        score += second;
        score += first;
        second = first = 0;
        third = 1;
    }

    private static void tworuta() {
        score += third;
        score += second;
        third = first;
        second = 1;
        first = 0;
    }

    private static void anta() {
        score += third;
        third = second;
        second = first;
        first = 1;
    }
}