import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_BJ_5052_전화번호_목록 {

    static int N;
    static Node root;

    // 트라이 자료구조 사용
    // 노드마다 1)이 노드를 끝으로 하는 문자열 유무, 2) 10개 노드 자식 을 가짐
    // 전화번호 하나씩 트라이에 추가할 때 마다 root에서부터 타고 내려가서
    // 방문하는 노드가 마지막인 문자열 있는지 확인,
    // 내가 마지막 노드인데 이미 누가 왔던 노드인지 확인 하면서 새로운 노드 추가해 감
    // 중간에 에러 안나면 정상적으로 insert 된 것
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        ArrayList<String> address;
        String yes = "YES", no = "NO", ret;

        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            address = new ArrayList<>();

            // br.readLine()으로 하나씩 로직 돌릴거면서
            // 중간에 return으로 멋대로 끊지 말아야 한다
            // 아니면 처음부터 입력값 다 받고난 뒤에 돌려야 한다 멍청인가봐
            for(int i=0; i<N; i++) {
                address.add(br.readLine());
            }

            root = new Node();
            ret = yes;

            for (String a: address){
                if(!insert(a.toCharArray())){
                    ret = no;
                    break;
                }
            }
            sb.append(ret+"\n");
        }

        System.out.println(sb);
    }

    private static boolean insert(char[] address) {
        Node cur = root;
        int num;
        for(int a=0, n=address.length; a<n; a++){
            num = address[a] - '0';
            if(cur.isEnd)   return false;
            if(cur.child[num]==null) cur.child[num] = new Node();
            else if(a==n-1) return false;
            cur = cur.child[num];
        }
        cur.isEnd = true;
        return true;
    }

    static class Node{
        boolean isEnd;
        Node[] child;

        public Node(){
            isEnd = false;
            child = new Node[10];
        }
    }

// 트라이 안쓰고 문자열 비교로 풀 수도 있다. 천재인가봐
    public void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            // 힙 만들어서 사전 순으로 정렬
            PriorityQueue<String> pq = new PriorityQueue<>();

            for (int j = 0; j < n; j++) {
                pq.add(br.readLine());
            }

            // 사전 상에서 가장 빠른 문자열
            String last = pq.poll();
            String ans = "YES";

            // 큐가 빌 때까지 하나씩 뽑으면서 바로 옆의 문자열끼리 비교
            // abc, abcd, abd, ... 이런 순으로 정렬되니까 옆문자열끼리는
            //   1. 접두어 관계
            //   2. 끝자리가 다름
            // 둘 중 하나임
            while(!pq.isEmpty()){
                String now = pq.poll();
                if (now.startsWith(last)){
                    ans = "NO";
                    break;
                }
                last = now;
            }
            sb.append(ans+"\n");
        }
        System.out.print(sb);
    }
}