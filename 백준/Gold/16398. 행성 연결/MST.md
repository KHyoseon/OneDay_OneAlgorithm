### kruskal
```java
private static void kruskal() {
	int[] parent;
    
    // parent 초기화
    for(int i=0; i<N; i++) {
        parent[i] = i;
    }
    
	// Edge의 weight 오름차 순으로 정렬
	PriorityQueue<Edge> edges;
    
    int N = edges.length;
    int cnt = 0;
	
    for(int i=0; i<N && cnt<N-1; i++) {
        ed = edges.poll();
        
        // 추가해서 사이클이 만들어지는가
        if(find(ed.v1) == find(ed.v2)) continue;
        
        // 안만들어지면 추가 (union find)
        union(ed);
        cnt++;
    }
}

private static void union(Edge ed) {
    int p1 = find(ed.v1);
    int p2 = find(ed.v2);
    parent[p1] = p2;
}

private static int find(int v) {
    if(parent[v] == v) return v;
    return parent[v] = find(parent[v]);
}
```

### Prim
```java
private static long Prim(){
    // weight 저장된 인접 배열
    int[][] weight;
    
    int[] nearest = new int[N];
    
    // i 노드와 트리까지의 거리
    // 갈 수 없는 경우, 이미 트리에 속한 경우: -1
    int[] distance = new int[N];
    
    long answer = 0;

	// 0번 노드를 기준으로 nearest, distance 초기화
    for(i=1; i<N; i++) {
        nearest[i] = 0;
        distance[i] = weight[0][i];
    }
    
    // 트리에 다음으로 추가할 가장 가까운 노드의 번호
    int near;
    
    for(int j=0; j<N-1; j++) {
        min = Integer.MAX_VALUE;
        
        // 트리에서 가장 가까운 노드를 찾아서 near에 저장
        for(int i=1; i<N; i++) {
        	// 트리로 올 수 있다 && 제일 가깝다
            if(0<=distance[i] && distance[i]<min) {
                min = distance[i];
                near = i;
            }
        }

		// near는 이제 트리에 속함
        distance[near] = -1;
        
        // 트리에 near를 새로 추가하여 더 짧아진 거리가 있으면 갱신
        for(int i=1; i<N; i++) {
            if(weight[i][near] < distance[i]) {
                distance[i] = weight[i][near];
                nearest[i] = near;
            }
        }
    }
}
```
