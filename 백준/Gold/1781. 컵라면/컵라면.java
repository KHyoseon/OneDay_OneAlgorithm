import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int lastDay = 0;

        HashMap<Integer, ArrayList<Quest>> map = new HashMap<>();

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            int dl = Integer.parseInt(input[0]);
            map.computeIfAbsent(dl, integer -> new ArrayList<>());
            map.get(dl).add(new Quest(i + 1, dl, Integer.parseInt(input[1])));
            lastDay = Math.max(lastDay, dl);
        }

        PriorityQueue<Quest> heap = new PriorityQueue<>(((o1, o2) -> o2.noodles - o1.noodles));

        long answer = 0;

        for(int day=lastDay; day>0; day--) {
            if(map.containsKey(day))
                heap.addAll(map.get(day));
            if(!heap.isEmpty())
                answer += heap.poll().noodles;
        }

        System.out.println(answer);
    }
}

class Quest {
    int no, deadLine, noodles;

    public Quest(int no, int deadLine, int noodles) {
        this.no = no;
        this.deadLine = deadLine;
        this.noodles = noodles;
    }
}