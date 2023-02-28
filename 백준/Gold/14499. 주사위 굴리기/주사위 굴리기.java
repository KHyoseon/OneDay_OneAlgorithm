import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] coor, order, dice;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        coor = new int[2];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        coor[0] = Integer.parseInt(st.nextToken());
        coor[1] = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[K];
        dice = new int[7];
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int k=0; k<K; k++) {
            order[k] = Integer.parseInt(st.nextToken());
        }

        // 주사위 1: 윗면, 2: 북쪽, 3: 동쪽, 4: 서쪽, 5: 남쪽, 6: 바닥면
        for(int direction: order) {
            roll(direction-1, coor);
        }
    }

    // 동 서 북 남
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    private static int[] roll(int direction, int[] coor) {
        int nx = coor[0] + dx[direction];
        int ny = coor[1] + dy[direction];

        if(nx < 0 || N <= nx || ny < 0 || M <= ny) return null;

        rollDice(direction);

        if(map[nx][ny] == 0){
            map[nx][ny] = dice[6];
        }
        else{
            dice[6] = map[nx][ny];
            map[nx][ny] = 0;
        }

        coor[0] += dx[direction];
        coor[1] += dy[direction];

        System.out.println(dice[1]);
        return coor;
    }

    private static void rollDice(int direction) {
        int temp;
        switch (direction) {
            case 0: // 동
                temp = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = temp;
                break;
            case 1: // 서
                temp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = temp;
                break;
            case 2: // 북
                temp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = temp;
                break;
            case 3: // 남
                temp = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                break;
        }
    }
}