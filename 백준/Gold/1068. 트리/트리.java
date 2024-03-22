import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, AVOID, cnt=0;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        for(int i=0; i<N; i++){
            tree[i] = new Node();
        }

        ArrayList<Integer> roots = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if(p != -1)
                tree[p].addChildren(i);
            else
                roots.add(i);
            tree[i].parent = p;
        }
        AVOID = Integer.parseInt(br.readLine());

        for(int root: roots)
            dfs(root);

        System.out.println(cnt);
    }

    private static void dfs(int root) {
        if(root == AVOID) return;
        if(tree[root].children.contains(AVOID)) {
            tree[root].children.remove(AVOID);
        }
        if(tree[root].children.size() == 0){
            cnt++;
//            System.out.println(root);
            return;
        }
        for(int child: tree[root].children) {
            dfs(child);
        }
    }

    static class Node{
        int parent;
        Set<Integer> children = new HashSet<>();

        public void addChildren(int child) {
            children.add(child);
        }
    }
}