import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution_PG_64064_불량_사용자 {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        System.out.println((new Solution()).solution(user_id, banned_id));
    }

    static class Solution {
        static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        static int[] pick;
        static Set<Integer> used = new HashSet<>();
        static int cnt=0;
        public int solution(String[] user_id, String[] banned_id) {
            int l = banned_id.length;
            pick = new int[l];
            for(int i=0; i<l; i++) {
                if(map.containsKey(banned_id[i])){
                    pick[i]++;
                    continue;
                }
                for(int j=0, n=user_id.length; j<n; j++) {
                    if(fitWildcard(user_id[j], banned_id[i])) {
                        if(!map.containsKey(banned_id[i]))
                            map.put(banned_id[i], new ArrayList<>());
                        map.get(banned_id[i]).add(j);
                    }
                }
            }

            go(0, banned_id, new int[l]);

            return cnt;
        }

        private boolean fitWildcard(String id, String wildcard) {
            if(id.length() != wildcard.length()) return false;
            for(int i=0, n=id.length(); i<n; i++){
                if(wildcard.charAt(i)!='*' && id.charAt(i)!=wildcard.charAt(i))
                    return false;
            }
            return true;
        }

        private void go(int cur, String[] banned_id, int[] set) {
            if (cur==banned_id.length){
                ++cnt;
                return;
            }
            for(Integer index: map.get(banned_id[cur])){
                if(used.contains(index)) continue;
                nPr(pick[cur]);
                used.add(index);
                go(cur+1, banned_id, set);
                used.remove(index);
            }
        }
    }
}
