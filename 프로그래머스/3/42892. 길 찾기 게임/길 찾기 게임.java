import java.util.*;

class Solution {
    class Node {
        int no, level, value;
        Node left, right;
        public Node(int no, int level, int value) {
            this.no = no;
            this.level = level;
            this.value = value;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;

        Node[] temp = new Node[n];
        for(int i=0; i<n; i++) {
            temp[i] = new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]);
        }

        Arrays.sort(temp,
                (o1, o2) -> o2.level==o1.level? o1.value-o2.value: o2.level-o1.level);

        Node root = temp[0];
        for(int i=1; i<n; i++) {
            insert(root, temp[i]);
        }

        int[][] answer = new int[2][n];

        prefix(root, answer);
        postfix(root, answer);

        return answer;
    }

    public void insert(Node parent, Node cur){
        if(cur.value < parent.value) {
            if(parent.left != null)
                insert(parent.left, cur);
            else
                parent.left = cur;
        } else {
            if(parent.right != null)
                insert(parent.right, cur);
            else
                parent.right = cur;
        }
    }

    int preidx = 0, postidx = 0;

    public void prefix(Node root, int[][] arr) {
        if(root == null) return;
        arr[0][preidx++] = root.no;
        prefix(root.left, arr);
        prefix(root.right, arr);
    }

    public void postfix(Node root, int[][] arr) {
        if(root == null) return;
        postfix(root.left, arr);
        postfix(root.right, arr);
        arr[1][postidx++] = root.no;
    }
}