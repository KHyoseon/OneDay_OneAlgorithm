import java.util.*;

class Solution {
    List<Match> matchList = new ArrayList<>();
    Set<Integer> domain = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {

        Arrays.sort(banned_id, Comparator.reverseOrder());

        int count;
        for(int bi=0, l=banned_id.length; bi<l; bi+=count){
            if(bi==l-1){
                matchList.add(new Match(banned_id[bi], 1));
                break;
            }
            count=1;
            for(int max=l-bi; count<max && banned_id[bi].equals(banned_id[bi+count]); count++);
            matchList.add(new Match(banned_id[bi], count));
        }

        for(Match match: matchList){
            for(int ui=0, l=user_id.length; ui<l; ui++){
                if(fitWildcard(user_id[ui], match.n)){
                    match.ids.add(ui);
                }
            }
        }

        nPr(0, matchList.get(0).r, 0, banned_id.length, 0);

        return domain.size();
    }

    private boolean fitWildcard(String id, String wildcard) {
        if(id.length() != wildcard.length()) return false;
        for(int i=0, n=id.length(); i<n; i++){
            if(wildcard.charAt(i)!='*' && id.charAt(i)!=wildcard.charAt(i))
                return false;
        }
        return true;
    }

    private void nPr(int n, int r, int start, int total, int set){
        // nPr 하나 완성 -> 다음 key에서 다시 nPr 시작
        if(r == 0){
            if(n != matchList.size()-1)
                nPr(n+1, matchList.get(n+1).r, 0, total, set);
            else if(total==0)
                domain.add(set);
            return;
        }

        List<Integer> ids = matchList.get(n).ids;
        for(int i=start; i<ids.size(); i++){
            if((set>>ids.get(i)&1) == 1)    continue;
            nPr(n, r-1, i+1, total-1, set+(1<<ids.get(i)));
        }
    }

    class Match {
        String n;
        int r;
        ArrayList<Integer> ids;

        public Match(String n, int r){
            this.n = n;
            this.r = r;
            this.ids = new ArrayList<>();
        }

        public void addId(int id) {
            this.ids.add(id);
        }
    }

}