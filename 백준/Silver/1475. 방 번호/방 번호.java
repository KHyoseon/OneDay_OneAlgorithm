import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[9];
        int room = Integer.parseInt(br.readLine());

        while(room > 0) {
            int num = room%10;
            if(num == 9) num = 6;
            nums[num]++;
            room /= 10;
        }
        nums[6] = nums[6]/2 + nums[6]%2;

        int max = -1;
        for(int i=0; i<9; i++) {
            max = Math.max(max, nums[i]);
        }

        System.out.println(max);
    }
}