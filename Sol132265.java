import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Sol132265 {
    public static void main(String[] args) {
        int[] topping = {1,2,1,3,1,4,1,2};
//        int[] topping = {1,2,3,1,4};
        System.out.println((new Solution()).solution(topping));
    }

    static class Solution {
        public int solution(int[] topping) {
            int cnt = 0;

            HashMap<Integer, Integer> older = new HashMap<>();
            HashMap<Integer, Integer> younger = new HashMap<>();

            for (int t : topping) {
                if (younger.containsKey(t))
                    younger.put(t, younger.get(t) + 1);
                else
                    younger.put(t, 1);
            }

            for (int j : topping) {
                if (slice(older, younger, j)) ++cnt;
            }

            return cnt;
        }

        private boolean slice(HashMap<Integer, Integer> older, HashMap<Integer, Integer> younger, int t) {
            if (older.containsKey(t))
                older.put(t, older.get(t) + 1);
            else
                older.put(t, 1);

            if (younger.containsKey(t)) {
                if (younger.get(t) == 1) younger.remove(t);
                else younger.put(t, younger.get(t) - 1);
            } else
                younger.put(t, 1);

            return older.size() == younger.size();
        }

    }

    class SolutionWithArray {
        public int solution(int[] topping) {
            int answer = 0;
            int[] left = new int[10001], right = new int[10001];
            // set 크기
            int ls = 0, rs = 0;
            // 오른쪽 그룹에 토핑 종류 표기
            for (int i : topping) right[i]++;
            // 토핑 개수가 1개 이상이면 set에 카운트
            for (int i : right) rs += i > 0 ? 1 : 0;
            // 오른쪽 그룹에 있던 토핑을 왼쪽부터 차례로 삭제, 왼쪽 그룹에 넣음
            for (int i : topping) {
                // 옮기기
                right[i]--;
                if (right[i] == 0) rs--;
                if (left[i] == 0) ls++;
                left[i]++;
                // 다 옮긴 후 set의 크기가 같으면 answer 카운트
                if (rs == ls) answer++;
            }
            return answer;
        }
    }

    class SolutionWithSet {
        public int solution(int[] topping) {
            // dp 느낌??
            // 왼쪽/오른쪽에서 i번째 까지 담았을 때 가질 수 있는 토핑의 가짓수
            int[] left = new int[topping.length];
            int[] right = new int[topping.length];

            Set<Integer> LeftTopping = new HashSet<>();
            Set<Integer> RightTopping = new HashSet<>();

            // left에는 왼쪽부터 i개 / right에는 오른쪽부터 i개
            for (int i = 0; i < topping.length; i++) {
                // RIndex ~ topping.length = i
                int RIndex = topping.length - i - 1;
                // 각 left, right set에 토핑 하나씩 추가
                LeftTopping.add(topping[i]);
                RightTopping.add(topping[RIndex]);
                // 하나씩 추가했을 때의 set 크기를 저장
                left[i] = LeftTopping.size();
                right[RIndex] = RightTopping.size();
            }

            // 왼~오에서 하나씩 추가했을 때 사이즈가 같아지는 절단면을 찾음
            return (int) IntStream.range(0, topping.length - 1).filter(i -> left[i] == right[i + 1]).count();
        }
    }
}