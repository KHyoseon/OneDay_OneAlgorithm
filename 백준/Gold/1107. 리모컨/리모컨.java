import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Set<Integer> broken;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 고장난 버튼들
        broken = new HashSet<>(M);

        // 최소 움직임 횟수 (100에서 +,- 버튼으로만 움직인 횟수로 초기화)
        int answer = Math.abs(100 - N);

        // 고장난 버튼이 없을 경우 -> 100에서 이동 VS 바로 이동
        if(M == 0) {
            System.out.println(Math.min(answer, getLength(N)));
            return;
        }
        // 모두 고장난 경우 -> 100에서 이동
        if(M == 10){
            System.out.println(answer);
            return;
        }

        // 일부만 고장난 경우
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            broken.add(Integer.parseInt(st.nextToken()));

        if(canType(N)){
            answer = Math.min(answer, getLength(N));
        }

        // 직접 이동할 수 있고 목표 번호(N)에 가장 가까운 번호 2개
        // min: N보다 작은 수 중 가장 큰 수
        // max: N보다 큰 수 중 가작 작은 수
        int min = N, max = N;
        // max/min에서 N까지 최소 이동 횟수 결과
        int rmax=-1, rmin=-1;
        // cnt: +,- 버튼 누른 횟수
        int cnt=0;

        // +,-을 cnt만큼 눌렀을 때 N에 도달할 수 있는 수를 찾고
        // 그 수가 직접 이동이 가능하다면 횟수를 계산해 answer 갱신
        while(rmax==-1 || rmin==-1){
            max++; min--;
            ++cnt;

            // 가지치기 (+,- 이동만으로 최소 움직임을 넘으면 더이상 탐색하지 않음)
            if (cnt > answer) break;

            // 직접 이동 가능하면 움직임 횟수 계산, answer 갱신
            if(canType(max)){
                rmax = getLength(max) + cnt;
                answer = answer>rmax? rmax: answer;
            }
            if(0<=min && canType(min)){
                rmin = getLength(min) + cnt;
                answer = answer>rmin? rmin: answer;
            }
        }

        System.out.println(answer);
    }

    private static boolean canType(int num) {
        // num의 각 자릿수에 고장난 버튼이 없으면 T, 있으면 F
        if(num==0){
            if(broken.contains(0)) return false;
            else return true;
        }
        while(num > 0){
            if(broken.contains(num%10)) return false;
            num/=10;
        }
        return true;
    }

    private static int getLength(int num) {
        // num으로 직접 이동하기 위해 버튼을 누르는 횟수
        int ret = 0;
        if(num==0) return 1;
        while(num > 0){
            ++ret;
            num/=10;
        }
        return ret;
    }
}