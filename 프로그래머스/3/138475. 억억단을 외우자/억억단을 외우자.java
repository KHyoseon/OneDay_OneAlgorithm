import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        // 약수 개수 다 찾기
        int[] yaksu = new int[e+1];
        
        for(int n1=1; n1<=e; n1++) {
            for(int n2=1; n2<=e/n1; n2++) {
                yaksu[n1*n2]++;
            }
        }
        
        // 누적 범위 내 원하는 수 저장
        // arr[i] = [i~e] 사이에 약수가 가장 많은 idx 저장
        int arr[] = new int[e+1];
        arr[e] = e;
        for(int s=e; 1<s; s--) {
            arr[s-1] = yaksu[arr[s]]<=yaksu[s-1]? s-1: arr[s];
        }
        
        int[] answer = new int[starts.length];
        for(int i=0, l=starts.length; i<l; i++)
            answer[i] = arr[starts[i]];
        
        return answer;
    }
}