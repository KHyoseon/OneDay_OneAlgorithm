### kruskal
```java
private static void kruskal() {
    for(Edge ed: roads) {
        if(money.size() == N-1) break;
        if(selected[ed.v1] && selected[ed.v2]) continue;
        // 추가해서 사이클이 만들어지는가
        if(makeCicle(ed)) continue;
        // 안만들어지면 되면 추가
        selected[ed.v1] = selected[ed.v2] = true;
        parent[ed.v2] = ed.v1;
        money.add(ed.weight);
    }
}

private static boolean makeCicle(Edge ed) {
    int p1 = getParent(ed.v1);
    int p2 = getParent(ed.v2);
    if(p1 == p2) return true;
    return false;
}

private static int getParent(int v) {
    int p = v;
    for (; parent[p] != 0; p=parent[p]);
    return p;
}

static class Edge implements Comparable<Edge> {
    int v1, v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
```

### Prim
```java
private static long Prim(){
    int i, near=0, min;
    int[] nearest = new int[N];
    int[] distance = new int[N];
    long answer = 0;

    for(i=1; i<N; i++) {
        nearest[i] = 0;
        distance[i] = weight[0][i];
    }
    for(int j=0; j<N-1; j++) {
        min = Integer.MAX_VALUE;
        for(i=1; i<N; i++) {
            if(0<=distance[i] && distance[i]<min) {
                min = distance[i];
                near = i;
            }
        }
        answer += min;
        distance[near] = -1;
        for(i=1; i<N; i++) {
            if(weight[i][near] < distance[i]) {
                distance[i] = weight[i][near];
                nearest[i] = near;
            }
        }
    }
    return answer;
}
```
