import java.util.*;

class Solution {
    int l;
    HashMap<String, Node> map = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        l = enroll.length;
        // map에 판매원 정보 저장
        for(int i=0; i<l; i++) {
            map.put(enroll[i], new Node(enroll[i], referral[i], 0));
        }
        
        for(int i=0, s=seller.length; i<s; i++) {
            int money = amount[i] * 100;
            int charge;
            
            // System.out.printf("%s가 %d원 벌었음 =>\n", seller[i], money);
            Node me = map.get(seller[i]);
            
            do {
                // 부모한테 줄 거 떼고
                charge = (int) (money * 0.1);
                // 나머지 내가 먹음
                // System.out.printf("\t%s: %d원 가짐, 나머지 %s 줌\n", me.name, money-charge, me.parent);
                me.money += (money-charge);
                
                // 내가 루트면 종료
                if(me.parent.equals("-")) break;
                
                // 나 -> 부모 갱신
                me = map.get(me.parent);
                // 수익금 갱신
                money = charge;
            } while(charge > 0);
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<l; i++) {
            answer.add(map.get(enroll[i]).money);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
}

class Node {
    String name;
    String parent;
    int money;
    
    public Node(String name, String parent, int money) {
        this.name = name;
        this.parent = parent;
        this.money = money;
    }
}