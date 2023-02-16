import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(64);

        int shortest;
        int sum = 64;

        while(sum != X && !heap.isEmpty()){
            shortest = heap.poll();
            shortest /= 2;
            heap.add(shortest);
            if(sum - shortest < X)
                heap.add(shortest);
            else
                sum -= shortest;
        }

        System.out.println(heap.size());
    }
}