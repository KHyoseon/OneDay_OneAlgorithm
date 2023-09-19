import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Block[] conveyor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        conveyor = new Block[2 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            conveyor[i] = new Block(false, Integer.parseInt(st.nextToken()));
        }

        int time=0;

        while(true) {
            time++;

            // 컨베이어 벨트 이동
            moveConveyor();

            // 로봇 이동
            moveRobots();
            if(0 >= K) break;

            // 1번에 로봇 내려 둠
            putOnFirst();
            if(0 >= K) break;
        }

        System.out.println(time);
    }

    private static void moveConveyor() {
        Block temp = conveyor[2 * N - 1];
        if(temp.robot) temp.robot = false;

        for(int i=2*N-1; i>0; i--) {
            conveyor[i] = conveyor[i - 1];
        }
        conveyor[N-1].robot = false;
        conveyor[0] = temp;
    }

    private static void moveRobots() {
        for(int i=N-1; i>0; i--) {
            if(conveyor[i].robot || !conveyor[i-1].robot || conveyor[i].durability <= 0) continue;
            conveyor[i].durability--;
            conveyor[i].robot = true;
            conveyor[i-1].robot = false;
            if(conveyor[i].durability == 0) K--;
        }
        conveyor[N-1].robot = conveyor[0].robot = false;
    }

    private static void putOnFirst() {
        if(conveyor[0].durability <= 0) return;
        conveyor[0].robot = true;
        conveyor[0].durability--;
        if(conveyor[0].durability == 0) K--;
    }
}

class Block {
    boolean robot;
    int durability;

    public Block(boolean robot, int durability) {
        this.robot = robot;
        this.durability = durability;
    }

    @Override
    public String toString() {
        return String.format("%d/%2d", this.robot?1:0, this.durability);
    }
}