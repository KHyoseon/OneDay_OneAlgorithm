package code;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BJ_1031_주사위 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = 51;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, dice[i]);
            sum += dice[i];
        }

        BigInteger answer = new BigInteger("0");
        long minPair = 200;

        for(int i=0; i<5; i++){
            for(int j=i+1; j<6; j++){
                if(i+j == 7) continue;
                minPair = Math.min(minPair, dice[i] + dice[j]);
            }
        }

        long minTriple = 300;
        int triple[] = new int[9];

        triple[0] = dice[0] + dice[1] + dice[2];
        triple[1] = dice[0] + dice[1] + dice[3];
        triple[2] = dice[0] + dice[2] + dice[4];
        triple[3] = dice[0] + dice[3] + dice[4];
        triple[4] = dice[1] + dice[2] + dice[5];
        triple[5] = dice[1] + dice[3] + dice[5];
        triple[6] = dice[2] + dice[3] + dice[4];
        triple[7] = dice[2] + dice[4] + dice[5];
        triple[8] = dice[3] + dice[4] + dice[5];

        for (int i = 0; i < 9; i++) {
            minTriple = Math.min(minTriple, triple[i]);
        }

        System.out.println(min + " " + minPair +" " + minTriple);
        answer = answer.add(new BigInteger(((N - 2) * (N - 2) * min) + ""));
        answer = answer.add(new BigInteger((4L * (N-1)*(N-2) * min) + ""));
        answer = answer.add(new BigInteger(((4*(2L*N - 3)) * minPair)  + ""));
        answer = answer.add(new BigInteger((4*minTriple)  + ""));
        System.out.println(answer);

    }
}
