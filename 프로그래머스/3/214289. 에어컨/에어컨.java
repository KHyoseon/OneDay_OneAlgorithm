import java.util.Arrays;

class Solution {
    int[][] dp;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;

        dp = new int[n][51];

        for(int i=0; i<n; i++)
            Arrays.fill(dp[i], (int)10e6);

        temperature += 10;
        t1 += 10;
        t2 += 10;
        int min = Math.min(temperature, t1);
        int max = Math.max(temperature, t2);

        dp[0][temperature] = 0;

        int last1 = 0;
        for(int cur=1; cur<n; cur++) {
            if(onboard[cur] == 1) last1 = cur;

            for(int curTemp=min; curTemp<=max; curTemp++) {
                // 승객이 있으면 [t1, t2] 범위 밖의 온도를 계산할 필요 없음
                if(onboard[cur] == 1 && (curTemp<t1 || t2<curTemp)) continue;

                // [i-1][j-1], [i-1][j], [i-1][j+1] 중 최솟값 선택
                for(int prevTemp=curTemp-1; prevTemp<=curTemp+1; prevTemp++) {
                    if(prevTemp < 0 || 50 < prevTemp) continue;
                    int watt;
                    if (Math.abs(temperature - prevTemp) < Math.abs(temperature - curTemp)) {
                        // 에어컨 켜서 역행해야 함
                        watt = a;
                    } else if (Math.abs(temperature - prevTemp) > Math.abs(temperature - curTemp)) {
                        // 에어컨 안켜고 놔둠
                        watt = 0;
                    } else if (temperature == curTemp) {
                        // 에어컨 안켜고 놔둠
                        watt = 0;
                    } else {
                        // 에어컨 켜서 온도 유지
                        watt = b;
                    }
                    dp[cur][curTemp] = Math.min(dp[cur][curTemp], dp[cur-1][prevTemp] + watt);
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int j=t1; j<=t2; j++) {
            answer = Math.min(answer, dp[last1][j]);
        }

        return answer;
    }
}