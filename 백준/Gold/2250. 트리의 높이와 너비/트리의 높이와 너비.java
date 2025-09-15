import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    static class Node {
        int level, column;
        int parent, left, right;
        public Node(){}

        public void set(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree;
    static TreeMap<Integer, int[]> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];
        for (int i = 1; i < N+1; i++) {
            tree[i] = new Node();
        }

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int no = Integer.parseInt(input[0]);
            int left = Integer.parseInt(input[1]);
            int right = Integer.parseInt(input[2]);
            tree[no].set(left, right);
            if(left != -1)
                tree[left].parent = no;
            if(right != -1)
                tree[right].parent = no;
        }

        int root = 0;
        for (int i = 1; i < N+1; i++) {
            if(tree[i].parent == 0) {
                root = i;
                break;
            }
        }

        tree[root].level = 1;
        inorder(root);

        int MAX = -1, LEVEL = 1;
        int MAX_LEVEL = map.size();
        for(int level=1; level<MAX_LEVEL; level++){
            int[] range = map.get(level);
            int width = tree[range[1]].column - tree[range[0]].column + 1;
            if(MAX < width) {
                MAX = width;
                LEVEL = level;
            }
        }
        int[] range = map.get(MAX_LEVEL);
        int width = tree[range[1]].column - tree[range[0]].column + 1;
        if(MAX < width) {
            MAX = width;
            LEVEL = MAX_LEVEL;
        }

        System.out.println(LEVEL + " " + MAX);
    }

    static int COL = 1;
    private static void inorder(int root) {
        Node cur = tree[root];

        // left tree
        if(cur.left != -1) {
            tree[cur.left].level = cur.level + 1;
            inorder(cur.left);
        }
        // cur
        cur.column = COL++;
        // map 갱신
        if(!map.containsKey(cur.level)){
            map.put(cur.level, new int[]{root, root});
        } else {
            int[] range = map.get(cur.level);
            if (tree[range[0]].column > cur.column) {
                map.get(cur.level)[0] = root;
            } else if(tree[range[1]].column < cur.column) {
                map.get(cur.level)[1] = root;
            }
        }

        // right tree
        if(cur.right != -1) {
            tree[cur.right].level = cur.level + 1;
            inorder(cur.right);
        }
    }
}