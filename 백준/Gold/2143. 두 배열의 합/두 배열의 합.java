import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int aN = Integer.parseInt(br.readLine());
        String[] input1 = br.readLine().split(" ");
        int bN = Integer.parseInt(br.readLine());
        String[] input2 = br.readLine().split(" ");

        int[] A = new int[aN];
        for(int i=0; i<aN; i++)
            A[i] = Integer.parseInt(input1[i]);

        int[] B = new int[bN];
        for(int i=0; i<bN; i++)
            B[i] = Integer.parseInt(input2[i]);

        ArrayList<Integer> aSum = new ArrayList<>();
        ArrayList<Integer> bSum = new ArrayList<>();

        for(int i=0; i<aN; i++) {
            aSum.add(A[i]);
            int temp = A[i];

            for(int j=i+1; j<aN; j++) {
                temp += A[j];
                aSum.add(temp);
            }
        }

        for(int i=0; i<bN; i++) {
            bSum.add(B[i]);
            int temp = B[i];

            for(int j=i+1; j<bN; j++) {
                temp += B[j];
                bSum.add(temp);
            }
        }

        Collections.sort(bSum);

        long SUM = 0;
        for(int aa: aSum) {
            int need = T - aa;
            int bound[] = binarySearch(need, bSum);
            if(bound[0] != -1)
                SUM += (bound[1] - bound[0]);
        }

        System.out.println(SUM);
    }

    private static int[] binarySearch(int need, ArrayList<Integer> bSum) {
        int l = 0, r = bSum.size();
        while(l < r) {
            int m = (l + r) / 2;
            if(bSum.get(m) < need) l = m + 1;
            else r = m;
        }
        int lower = l;

        // upper bound (need보다 큰 값이 처음 나오는 위치)
        l = 0; r = bSum.size();
        while(l < r) {
            int m = (l + r) / 2;
            if(bSum.get(m) <= need) l = m + 1;
            else r = m;
        }
        int upper = l;

        if(lower == upper) return new int[]{-1, -1}; // 존재하지 않음
        return new int[]{lower, upper};
    }
}