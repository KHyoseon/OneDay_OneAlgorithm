import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int[] finger = new int[8];

        for(char c: input) {
            switch (c) {
                case '1':
                case 'Q':
                case 'A':
                case 'Z':
                    finger[0]++;
                    break;
                case '2':
                case 'W':
                case 'S':
                case 'X':
                    finger[1]++;
                    break;
                case '3':
                case 'E':
                case 'D':
                case 'C':
                    finger[2]++;
                    break;
                case '4':
                case 'R':
                case 'F':
                case 'V':
                case '5':
                case 'T':
                case 'G':
                case 'B':
                    finger[3]++;
                    break;
                case '6':
                case 'Y':
                case 'H':
                case 'N':
                case '7':
                case 'U':
                case 'J':
                case 'M':
                    finger[4]++;
                    break;
                case '8':
                case 'I':
                case 'K':
                case ',':
                    finger[5]++;
                    break;
                case '9':
                case 'O':
                case 'L':
                case '.':
                    finger[6]++;
                    break;
                default:
                    finger[7]++;
            }
        }

        for(int f: finger)
            System.out.println(f);
    }
}