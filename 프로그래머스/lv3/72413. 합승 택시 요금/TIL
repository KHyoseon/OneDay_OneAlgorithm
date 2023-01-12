## 1. 다익스트라

특정 시작 지점에서 다른 모든 노드의 최단 거리를 구할 때 사용.

```
matrix[s][e] = s에서 e로 가는데 필요한 weight
dist[] = matrix[s] = s로부터 각 노드의 거리 (계속 갱신됨)
```

start와 가장 가까운 cur부터 탐색 (PQ 사용시 -> O(n*log(n)))

start에서 cur가 아닌 next로 이동할 때, cur을 거쳐가는게 더 빠른 next가 있다면 갱신

```
pq.addAll(dist)

for(cur : pq){
    for(next : (matrix[cur][next] != INF인 next들)) {
    
        // next로 바로 가는 것보다 cur을 거치는게 더 빠를 경우
        if(dist[next] > dist[cur] + matrix[cur][next]) {
            dist[next] = dist[cur] + matrix[cur][next]
            
            // 전보다 next를 더 빠르게 갈 수 있는 경로가 추가됨
            pq.add(next, dist[cur] + matrix[cur][next])
        }
    }
}
```


## 2. 플로이드 와샬

모든 노드에서 모든 노드까지의 최단 거리를 구할 때 사용.
```
matrix[s][e] = s에서 e의 최단거리 (계속 갱신)
```

모든 노드에서 모든 노드까지 ==
```
for(int s=0; s<n; s++)
    for(int e=0; e<n; e++)
```

도착지로 바로 이동하는 것(s -> e) VS 특정 노드 k를 거쳐 이동하는 것 (s -> k -> e)

더 빠른 것을 matrix[s][e]에 저장

```
for(int k=0; k<n; k++)
    for(int s=0; s<n; s++)
        for(int e=0; e<n; e++)
            matrix[][] = Math.min(matrix[][], matrix[][] + matrix[][])
```


---

이 문제에서는 
1. 다익스트라
   1. start, a, b를 시작점으로 두고 다익스트라를 세 번 실행. 각각 distS, distA, distB를 얻음
   2. distS[i] + distA[i] + distB[i]의 최솟값을 구함
2. 플로이드 와샬
   1. 플로이드 와샬 한번 실행. 갱신된 matrix 얻음
   2. matrix[i][s] + matrix[i][a] + matrix[i][b]의 최솟값을 구함

### 결과는 다익스트라가 더 빨랐다.
한 시작 지점에서 다른 모든 지점까지의 최단 거리를 구하는데 필요한 연산이 O(x)라면
다익스트라는 3*O(x) 인데 플로이드 워샬은 n*O(x)니까 더 느렸던 것 같다.
