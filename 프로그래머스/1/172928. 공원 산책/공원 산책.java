import java.util.*; 

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        
        int[] dog = new int[2];
        for(int r=0; r<park.length; r++) {
            for(int c=0; c<park[0].length(); c++) {
                if(park[r].charAt(c) == 'S') {
                    dog[0] = r;
                    dog[1] = c;
                    r = park.length;
                    break;
                }
            }
        }
        
        for(String route: routes) {
            char direct = route.toCharArray()[0];
            int dist = route.toCharArray()[2]-'0';
            move(direct, dist, dog, park);
        }
        
        return dog;
    }
    
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    
    public void move(char direct, int dist, int[] dog, String[] park) {
        int d=0;
        switch(direct) {
            case 'E':
                d=0; break;
            case 'S':
                d=1; break;
            case 'W':
                d=2; break;
            case 'N':
                d=3; break;
        }
        
        int nr = dog[0], nc = dog[1];
        
        for(int dd=1; dd<=dist; dd++) {
            nr += dr[d];
            nc += dc[d];
            
            if(nr<0 || nc<0 || park.length<=nr || park[0].length()<=nc) return;
            if(park[nr].charAt(nc) == 'X') return;
        }
        
        dog[0] = nr;
        dog[1] = nc;
        System.out.println(direct + " " + dist + " => " + Arrays.toString(dog));
    }
}