import java.util.*;

class Solution {
    String numbers;
    
    public int solution(String numbers) {
        this.numbers = numbers;
        
        func(0, "", new boolean[numbers.length()]);
        return set.size();
    }
    
    HashSet<Integer> set = new HashSet<>();
    
    public void func(int index, String str, boolean[] selected) {
        if(index>0 && check(Integer.parseInt(str))) {
            set.add(Integer.parseInt(str));
        }
        
        for(int i=0, N=selected.length; i<N; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            func(index+1, str+""+numbers.charAt(i), selected);
            selected[i] = false;
        }
    }
    
    public boolean check(int num) {
        if(num == 3 || num == 2) return true;
        if(num < 2 || num%2 == 0) return false;
        
        for(int div=3; div<=Math.sqrt(num); div++) {
            if(num % div == 0){
                return false;
            }
        }
        
        return true;
    }
}