import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] monkey = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][][] visited;

    static class Node {
        int r, c, k;
        public Node(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char map[][] = new char[H][W];
        visited = new boolean[H][W][K+1];

        if(W==1 && H==1) {
            System.out.println("0");
            return;
        }

        for(int i=0; i<H; i++) {
            map[i] = br.readLine().replace(" ", "").toCharArray();
        }

        // r=높이, c=너비
        int nr, nc;
        int move=0;

        Queue<Node> current = new LinkedList<>();
        visited[0][0][K] = true;
        current.add(new Node(0, 0, K));

        Node cur = null;
        while(!current.isEmpty()) {
            ++move;
            Queue<Node> next = new LinkedList<>();
            while(!current.isEmpty()) {
                cur = current.poll();

                for(int m = (cur.k == 0 ? 8: 0); m<12; m++) {
                    nr = cur.r + monkey[m][0];
                    nc = cur.c + monkey[m][1];

                    if(nr<0 || nr>=H || nc<0 || nc>=W || map[nr][nc] == '1' ||
                            visited[nr][nc][cur.k - (m<8? 1: 0)]) continue;

                    if(nr==H-1 && nc==W-1) {
                        System.out.println(move);
                        return;
                    }

                    visited[nr][nc][cur.k - (m<8? 1: 0)] = true;
                    next.add(new Node(nr, nc, (m<8? cur.k-1: cur.k)));
                }
            }
            current = next;
        }
        System.out.println("-1");
    }
}