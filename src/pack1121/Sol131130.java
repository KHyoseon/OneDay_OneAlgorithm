import java.util.*;

public class Sol131130 {
    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        System.out.println((new Solution()).solution(cards));
    }

    static class Solution {
        public int solution(int[] cards) {
            int answer = 0;
            int n = cards.length;

            int first, second;
            boolean[] opened, copied;

            for(int i=0; i<n; i++){
                opened = new boolean[n];
                first = simulate(i, opened, cards);
                if(first == n) continue;

                for(int j=0; j<n; j++){
                    copied = opened.clone();
                    if(i==j) continue;
                    second = simulate(j, copied, cards);
                    answer = answer>(first*second)?answer:(first*second);
                }
            }
            return answer;
        }

        public int simulate(int start, boolean[] copied, int[] cards){
            int cnt = 0;
            for(int next=start; !copied[next]; next = cards[next] - 1){
                copied[next] = true;
                ++cnt;
            }
            return cnt;
        }
    }

    // 1~N 선택했을 때 얻을 수 있는 그룹이 다 다르기 때문에
    // i, j 따로 안돌리고 걍 한번만 돌리도 가능했다.... 멍청이

    // 한번 싹 돌리고 그 중 차례로 가장 큰 두개 곱을 출력하면 됨
    class Solution2 {
        public int solution(int[] cards) {
            int n = cards.length;
            boolean[] visited = new boolean[n];
            List<Integer> groups = new ArrayList<>();

            // 모든 카드에 대해서 한번씩 simulate 함
            for (int i = 0; i < n; i++) {
                int now = i;
                int cnt = 0;
                // simulate
                // 근데 이제 이것도 1~N 다 돌리는게 아니라
                // 1을 돌렸을 때 1, 4, 6, 7이 그룹으로 묶였다면 얘네는 다 같이 4일거니까
                // 그룹으로 나온 애들은 visited=false 로 표기하고 따로 탐색하지 않는다.
                while (!visited[now]) {
                    cnt++;
                    visited[now] = true;
                    now = cards[now] - 1;
                }
                // i를 첫번째로 선택했을 때 그룹 사이즈가 얼마인지 적어 냄
                groups.add(cnt);
            }
            // 내림차 정렬
            Collections.sort(groups, Comparator.reverseOrder());
            // (groups.size() == 1) == 모든 카드가 한번에 다 루프를 돌 수 있다 == 0 반환
            return (groups.size() == 1) ? 0 : groups.get(0) * groups.get(1);
        }
    }
}