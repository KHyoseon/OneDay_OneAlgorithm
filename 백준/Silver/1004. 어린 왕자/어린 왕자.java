import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, x1, x2, y1, y2;
    static Circle[] circles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            N = Integer.parseInt(br.readLine());
            circles = new Circle[N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                circles[i] = new Circle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append(go()).append("\n");
        }
        System.out.println(sb);
    }

    private static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int go() {
        int answer = 0;
        for(Circle c: circles) {
            if ((dist(c.x, c.y, x1, y1) > c.r && dist(c.x, c.y, x2, y2) < c.r )
                || (dist(c.x, c.y, x1, y1) < c.r && dist(c.x, c.y, x2, y2) > c.r)) ++answer;
        }
        return answer;
    }

    static class Circle {
        int x, y, r;

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}