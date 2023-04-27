package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BJ_2263_트리의_순회 {
    static int N;
    static int[] inOrder, postOrder;
    static HashMap<Integer, Integer> io = new HashMap<>();
    static HashMap<Integer, Integer> po = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int i=0, key;
        while(st.hasMoreTokens()) {
            key = Integer.parseInt(st.nextToken());
            io.put(key, i);
            inOrder[i++] = key;
        }

        st = new StringTokenizer(br.readLine());
        i=0;
        while(st.hasMoreTokens()) {
            key = Integer.parseInt(st.nextToken());
            po.put(key, i);
            postOrder[i++] = key;
        }

        makePreOrder(N-1);

        /*int[] tree2 = {0, 1, 2, 4, 5, 3, 8, 0, 9, 10, 0, 7, 0, 0, 0, 0, 6};
        getInOrder(tree2, 1);
        System.out.println();
        getPostOrder(tree2, 1);*/

    }

    private static LinkedList<Integer> makePreOrder(int root) {
        LinkedList<Integer> tree = new LinkedList<>();
        // root
        tree.add(postOrder[root]);

        if(io.get(postOrder[root-1]) < io.get(postOrder[root])) {
            // left only
            tree.addAll(makePreOrder(root - 1));
        } else {
            // left
            tree.addAll(makePreOrder(root - 1));
            // right
            tree.addAll(makePreOrder(root - 1));
        }

        return tree;
    }

    private static void getInOrder(int[] tree2, int root) {
        if(root >= tree2.length) return;

        getInOrder(tree2, root*2);
        if(tree2[root] != 0)
            System.out.print(tree2[root]+" ");
        getInOrder(tree2, root*2+1);
    }
    private static void getPostOrder(int[] tree2, int root) {
        if(root >= tree2.length) return;

        getPostOrder(tree2, root*2);
        getPostOrder(tree2, root*2+1);
        if(tree2[root] != 0) {
            System.out.print(tree2[root]+" ");
        }
    }

    class Node {
        int n;
        Node[] child = new Node[2];

        public Node(int n) {
            this.n = n;
        }

        public Node getLeftChild() {
            return child[0];
        }

        public Node getRightChild() {
            return child[1];
        }
    }

}
