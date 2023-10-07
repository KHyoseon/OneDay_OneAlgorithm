import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Student {
        int satisfy;
        int x, y;
        boolean sitted = false;
        int[] like = new int[4];
    }

    public static Student students[];
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new Student[N * N];

        int[] order = new int[N * N];

        StringTokenizer st;
        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            // 학생 생성
            int ME = order[i] = Integer.parseInt(st.nextToken()) - 1;
            students[ME] = new Student();

            // 좋아하는 학생 받아옴
            for(int j=0; j<4; j++) {
                students[ME].like[j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        // 선생님 줄 세운 순서대로 배치
        int score[][] = new int[N][N];
        boolean taken[][] = new boolean[N][N];
        int empty[][] = new int[N][N];

        for(int i=1; i<N-1; i++) {
            Arrays.fill(empty[i], 4);
        }
        for(int j=0; j<N; j++) {
            empty[0][j] = empty[j][0] = 3;
            empty[N-1][j] = empty[j][N-1] = 3;
        }
        empty[0][0] = empty[0][N-1] = empty[N-1][0] = empty[N-1][N-1] = 2;

        int sum = 0;
        for(int std: order) {
            for(int i=0; i<N; i++) {
                Arrays.fill(score[i], 0);
            }

            Student ME = students[std];
            // 내가 좋아하는 애가 앉았는지 확인
            for(int frd: ME.like) {
                if(students[frd].sitted) {
                    // 앉아있는 애의 상하좌우에 점수 1점 추가
                    addScore(score, frd);
                }
            }

            // 가장 높은 점수의 칸에 앉음
            int MAX = -1;

            PriorityQueue<int[]> check = new PriorityQueue<>(((o1, o2) ->
                    empty[o2[0]][o2[1]] != empty[o1[0]][o1[1]] ? empty[o2[0]][o2[1]] - empty[o1[0]][o1[1]] :
                            (o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1])));
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(taken[i][j]) continue;
                    if(MAX < score[i][j]) {
                        MAX = score[i][j];
                        check.clear();
                        check.add(new int[]{i, j});
                    } else if(MAX == score[i][j]) {
                        check.add(new int[]{i, j});
                    }
                }
            }
            int[] sit = check.poll();
            ME.x = sit[0];
            ME.y = sit[1];
            ME.sitted = true;
            taken[sit[0]][sit[1]] = true;
            cntEmpty(empty, sit[0], sit[1]);

        }

        // 만족도
        // 11부터 NN까지 내 상하좌우에 앉은 좋은 학생 수 계산
        for(Student std: students) {
            for(int frd: std.like) {
                if(Math.abs(std.x-students[frd].x) + Math.abs(std.y-students[frd].y) == 1) {
                    std.satisfy++;
                }
            }
            if(std.satisfy != 0)
                sum += Math.pow(10, std.satisfy-1);
        }

        System.out.println(sum);

    }

    private static void cntEmpty(int[][] empty, int x, int y) {
        if(x-1 >= 0) {
            empty[x-1][y] -= 1;
        }
        if(y-1 >= 0) {
            empty[x][y-1] -= 1;
        }
        if(y+1 < N) {
            empty[x][y+1] -= 1;
        }
        if(x+1 < N) {
            empty[x+1][y] -= 1;
        }
    }

    private static void addScore(int[][] score, int frd) {
        int x = students[frd].x;
        int y = students[frd].y;

        if(x-1 >= 0) {
            score[x-1][y] += 1;
        }
        if(x+1 < N) {
            score[x+1][y] += 1;
        }
        if(y+1 < N) {
            score[x][y+1] += 1;
        }
        if(y-1 >= 0) {
            score[x][y-1] += 1;
        }
    }
}