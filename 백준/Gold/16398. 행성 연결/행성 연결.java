import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weight = new int[N][N];

        // 그래프 구성
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(Prim());
    }


    private static long Prim(){
        int i, near=0, min;
        int[] nearest = new int[N];
        int[] distance = new int[N];
        long answer = 0;

        for(i=1; i<N; i++) {
            nearest[i] = 0;
            distance[i] = weight[0][i];
        }
        for(int j=0; j<N-1; j++) {
            min = Integer.MAX_VALUE;
            for(i=1; i<N; i++) {
                if(0<=distance[i] && distance[i]<min) {
                    min = distance[i];
                    near = i;
                }
            }
            answer += min;
            distance[near] = -1;
            for(i=1; i<N; i++) {
                if(weight[i][near] < distance[i]) {
                    distance[i] = weight[i][near];
                    nearest[i] = near;
                }
            }
        }
        return answer;
    }
}