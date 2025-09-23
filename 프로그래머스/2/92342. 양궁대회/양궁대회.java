import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] ans = new int[11];
        // 10 to 0
        dp(0, n, 0, 0, ans, info);
        return answer;
    }

    int MAX = -1;
    int[] answer = {-1};

    public void dp(int i, int remain, int ryan, int apeach, int[] ans, int[] info) {
        // 남은 화살 개수 0, 더 못 쏨
        if(remain == 0) {
            // 이미 어피치보다 점수 낮으면 종료
            if(apeach >= ryan) return;
            // 어피치 남은 점수 계산
            for(; i<11; i++)
                if(info[i] > 0)
                    apeach += 10-i;
        }
        // 10번째 과녁까지 다 고려함
        if(i > 10) {
            // 어피치보다 점수 낮거나 점수 차가 최대보다 작으면 그냥 종료
            if(apeach >= ryan || MAX > (ryan-apeach)) return;

            int[] clone = ans.clone();

            // 남은 화살 있으면 현 점수 상황을 해치지 않는 선에서 낮은 점수 순으로 화살 추가함
            int j = 10;
            while(remain > 0) {
                // 아무도 안 쏜 과녁이면 넘어감
                if(clone[j] + info[j] == 0) {
                    --j; continue;
                }
                // 라이언이 가져간 점수면 거기에 몰빵
                if(clone[j] > info[j]) {
                    clone[j] += remain;
                    break;
                }
                // 어피치가 가져간 점수면 어피지 화살 개수 넘지 않을 때까지 추가
                while(remain > 0 && clone[j] < info[j]) {
                    clone[j]++;
                    remain--;
                }
                --j;
            }

            if(answer.length == 1 || MAX < (ryan-apeach)) {
                MAX = ryan - apeach;
                answer = clone;
                return;
            }

            for(j=10; j>=0; j--) {
                if(clone[j] == answer[j]) continue;
                if(clone[j] > answer[j]) {
                    answer = clone;
                }
                break;
            }
            return;
        }

        // 라이언 현재 점수 + 나머지 화살로 점수 하나씩 다 맞춰도 어피치보다 못하면 종료
        if(ryan + sumOf(i, remain) < MAX) return;

        if(remain > info[i]) {
            ans[i] = info[i] + 1;
            dp(i+1, remain - (info[i]+1), ryan + (10-i), apeach, ans, info);
            ans[i] = 0;
        }

        dp(i+1, remain, ryan, apeach + (info[i]==0? 0: (10-i)), ans, info);

    }

    public int sumOf(int i, int j) {
        int c = 10 - i;
        int k = c - j;
        return c*(c+1)/2 - k*(k+1)/2;
    }
}