import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for(int i=0, l=phone_book.length; i<l-1; i++){
            if(phone_book[i].length() >= phone_book[i+1].length()) continue;
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}