import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static HashMap<Character, Node> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            map.put((char) (65 + i), new Node((char) (65 + i)));
        }

        char[] input;
        char root = ' ';
        for(int i=0; i<N; i++) {
            input = br.readLine().toCharArray();
            map.get(input[0]).setChild(input[2], input[4]);
            if(root == ' ') root = input[0];
        }

        preorder(root);
        sb.append("\n");
        inorder(root);
        sb.append("\n");
        postorder(root);

        System.out.println(sb);
    }

    private static void postorder(char root) {
        if(map.get(root).left != '.')
            postorder(map.get(root).left);
        if(map.get(root).right != '.')
            postorder(map.get(root).right);
        sb.append(root);
    }

    private static void inorder(char root) {
        if(map.get(root).left != '.')
            inorder(map.get(root).left);
        sb.append(root);
        if(map.get(root).right != '.')
            inorder(map.get(root).right);
    }

    private static void preorder(char root) {
        sb.append(root);
        if(map.get(root).left != '.')
            preorder(map.get(root).left);
        if(map.get(root).right != '.')
            preorder(map.get(root).right);
    }

    static class Node {
        char me, left, right;

        public Node(char me) {
            this.me = me;
        }

        public void setChild(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }
}