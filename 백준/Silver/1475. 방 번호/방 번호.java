import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[9];
        char[] room = br.readLine().toCharArray();

        for(char card: room) {
            int index = card-'0';
            if(index == 9) index = 6;
            nums[index]++;
        }
        int div = nums[6]%2;
        nums[6] /= 2;
        nums[6] += div;

        int max = -1;
        for(int i=0; i<9; i++) {
            max = Math.max(max, nums[i]);
        }

        System.out.println(max);
    }
}