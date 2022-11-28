import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5052 {

    static int N;
    static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        root = new Node();

        for (int i=0; i<N; i++){
            insert(br.readLine());
        }


    }

    private static void insert(String address) {

    }

    static class Node{
        int num;
        boolean isEnd;
        Node[] child = new Node[26];
    }
}
