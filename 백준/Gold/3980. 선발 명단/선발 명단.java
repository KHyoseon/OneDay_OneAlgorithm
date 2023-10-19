import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Player>> position;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        position = new ArrayList<>(11);

        while(TC > 0) {
            for (int i = 0; i < 11; i++) {
                position.add(new ArrayList<>());
            }
            MAX = 0;

            int a;
            for(int i=0; i<11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++) {
                    a = Integer.parseInt(st.nextToken());
                    if(a == 0) continue;
                    position.get(j).add(new Player(i, a));
                }
            }

            int sum = 0;
            combi(0, new boolean[11], sum);
            sb.append(MAX).append("\n");

            position.clear();
            TC--;
        }
        System.out.print(sb);
    }

    static int MAX;

    private static void combi(int pos, boolean[] selected, int sum) {
        if(pos == 11) {
            MAX = Math.max(MAX, sum);
            return;
        }

        boolean nobody = true;
        for(Player player: position.get(pos)){
            if(selected[player.no]) continue;
            nobody = false;
            selected[player.no] = true;
            combi(pos + 1, selected, sum + player.ability);
            selected[player.no] = false;
        }
    }

    static class Player {
        int no, ability;
        public Player(int no, int ability) {
            this.no = no;
            this.ability = ability;
        }
    }
}