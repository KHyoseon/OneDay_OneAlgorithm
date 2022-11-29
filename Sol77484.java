import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sol77484 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println((new Solution()).solution(lottos, win_nums));
    }

    static class Solution{
        public int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = new int[2];

            Set<Integer> set = new HashSet<>();
            for(int w: win_nums){
                set.add(w);
            }

            Arrays.sort(lottos);
            int zero = 0, correct = 0;
            for(int i=0; i<6; i++){
                if(lottos[i]==0)    ++zero;
                else if(set.contains(lottos[i]))   ++correct;
            }

            switch (correct){
                case 6:
                    answer[1] = 1;
                    answer[0] = answer[1] - zero;
                    break;
                case 5:
                    answer[1] = 2;
                    answer[0] = answer[1] - zero;
                    break;
                case 4:
                    answer[1] = 3;
                    answer[0] = answer[1] - zero;
                    break;
                case 3:
                    answer[1] = 4;
                    answer[0] = answer[1] - zero;
                    break;
                case 2:
                    answer[1] = 5;
                    answer[0] = answer[1] - zero;
                    break;
                default:
                    answer[1] = 6;
                    answer[0] = (answer[1]==zero)?1:answer[1]-zero;
            }

            return answer;
        }
    }

    // Arrays.binarySearch 신기함...
    // 무조건 소팅된 배열을 넣으면 찾고자 하는 인수가 위치한 인덱스가 나온다. 여러개라면 그 중에 하나만 나옴
    class Solution2 {
        public int[] solution(int[] lottos, int[] win_nums) {
            int[] rank = {6, 6, 5, 4, 3, 2, 1};
            int answer = 0;
            int hidden = 0;

            Arrays.sort(win_nums);
            for (int i = 0; i < lottos.length; i++)
                // win_nums 에 내가 찍은게 있으면 ++answer 
                if (Arrays.binarySearch(win_nums, lottos[i]) > -1)
                    answer++;
                // 없고 값이 가려진 경우 hidden++;
                else if (lottos[i] == 0)
                    hidden++;
            // 0: hidden이 다 맞을 경우 등수, 1: 다 안맞을 경우 등수
            return new int[] {rank[answer + hidden], rank[answer]};
        }
    }
}
