## 이분탐색

중간 값을 보고 range를 조절

```java
while(l+1 < r) {
    mid = (l+r)/2;
    if (canGo(mid, stones, k)) {
        l = mid;
    } else {
        r = mid;
    }
}
```

조건 (canGo)

X+1 번째 사람이 건널 때 jump를 k번 이상해야 하는지 확인

-> 밟을 수 있는 횟수가 X와 같거나 그보다 작으면 점프해야함
-> 점프하지 않고 밟을 수 있다면 jump를 초기화함

```java
private boolean canGo(int people, int[] stones, int k) {
    int jump = 0;
    for(int canStep: stones){
        if(canStep < people){
            jump++;
            if(jump >= k) return false;
        }
        else {
            jump = 0;
        }
    }
    return true;
}
```

## 왜 내 Sliding window는 안됐을까?

사실 이분탐색으로 짜기 전에 슬라이딩 윈도우로 짰었다.

```java
public int solution(int[] stones, int k) {
    Queue<Integer> window = new LinkedList<>();

    int max = 0;
    for(int i=0; i<k; i++) {
        max = Math.max(max, stones[i]);
        window.add(stones[i]);
    }

    int poll, min = max;
    for(int i=k, l=stones.length; i<l && !window.isEmpty(); i++) {
        poll = window.poll();
        window.add(stones[i]);
        if(poll == max){
            max = maximum(window);
        }
        min = Math.min(min, max);
    }

    return min;
}
```

정확성도 다 맞았고, 효율성도 얼추 다 맞나 싶었는데 13번에서 시간초과가 났다.

질문을 보니까 효율성 13번 TC로 아마 200,000 사이즈의 { 200000000, 199999999, 199999998, ...} 내림차 배열이 주어진 듯 같다.

이 경우 내 코드로는 maximum을 구하는 함수(O(k))가 N번 호출되기 때문에 O(N**k) 시간 복잡도가 되어 시간초과가 난다.

이진탐색을 하는 이유는 X명이 건널 수 있는지 확인하는 함수(O(n))를 logN번만 하면 되기 때문에 좀 더 빠르게 수행할 수 있다.
