import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Lecture[] lectures;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    static class Lecture implements Comparable<Lecture> {
        int start, end;

        public Lecture(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start==o.start? this.end-o.end: this.start-o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N];

        int s, e;
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(s, e);
        }

        Arrays.sort(lectures);
        pq.add(lectures[0].end);

        int earliest;
        for(int i=1; i<N; i++) {
            earliest = pq.peek();
            if(earliest <= lectures[i].start) {
                pq.poll();
            }
            pq.add(lectures[i].end);
        }

        System.out.println(pq.size());
    }

}