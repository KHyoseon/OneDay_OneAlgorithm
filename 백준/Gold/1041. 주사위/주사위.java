import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = 51, max = 0;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, dice[i]);
            max = Math.max(max, dice[i]);
            sum += dice[i];
        }

        // 주사위가 하나면 가장 큰 값 제외한 5면 합 출력
        if(N==1){
            System.out.println(sum - max);
            return;
        }

        // 모든 면이 동일한 숫자일 경우
        int s = dice[0];
        int ss;
        for(ss=1; ss<6; ss++){
            if(dice[ss] != s) break;
        }
        if(ss == 6){
            BigInteger answer = new BigInteger((5*dice[0])+"");
            answer = answer.multiply(new BigInteger(N+""));
            answer = answer.multiply(new BigInteger(N+""));
            System.out.println(answer);
            return;
        }

        // 그 외
        long minPair = 200;

        for(int i=0; i<5; i++){
            for(int j=i+1; j<6; j++){
                if(i+j == 5) continue;
                minPair = Math.min(minPair, dice[i] + dice[j]);
            }
        }

        long minTriple = 300;
        int triple[] = new int[8];

        triple[0] = dice[0] + dice[1] + dice[2];
        triple[1] = dice[0] + dice[1] + dice[3];
        triple[2] = dice[0] + dice[2] + dice[4];
        triple[3] = dice[0] + dice[3] + dice[4];
        triple[4] = dice[1] + dice[2] + dice[5];
        triple[5] = dice[1] + dice[3] + dice[5];
        triple[6] = dice[2] + dice[4] + dice[5];
        triple[7] = dice[3] + dice[4] + dice[5];

        for (int i = 0; i < 8; i++) {
            minTriple = Math.min(minTriple, triple[i]);
        }

        BigInteger answer = new BigInteger("0");

        BigInteger first1 = new BigInteger(min + "");
        first1 = first1.multiply(new BigInteger((N - 2) + ""));
        first1 = first1.multiply(new BigInteger((N - 2) + ""));

        BigInteger first2 = new BigInteger(4*min + "");
        first2 = first2.multiply(new BigInteger((N - 1) + ""));
        first2 = first2.multiply(new BigInteger((N - 2) + ""));

        BigInteger second1 = new BigInteger((4*minPair)+"");
        second1 = second1.multiply(new BigInteger((N - 1) + ""));

        BigInteger second2 = new BigInteger((4*minPair)+"");
        second2 = second2.multiply(new BigInteger((N-2) + ""));

        BigInteger third = new BigInteger("1");
        third = third.multiply(new BigInteger((4*minTriple)  + ""));

        answer = answer.add(first1);
        answer = answer.add(first2);
        answer = answer.add(second1);
        answer = answer.add(second2);
        answer = answer.add(third);
        System.out.println(answer);

    }
}