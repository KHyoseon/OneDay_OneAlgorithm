import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int N = genres.length;

        // 장르별 총 재생 횟수로 장르 정렬
        HashMap<String, Integer> allGenre = new HashMap<>();
        for (int i = 0; i < N; i++) {
            allGenre.put(genres[i], allGenre.getOrDefault(genres[i], 0) + plays[i]);
        }

        for(String g: genres) allGenre.put(g, allGenre.getOrDefault(g, 0) + 1);
        int GS = allGenre.size();

        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> allGenre.get(o2) - allGenre.get(o1));
        pq.addAll(allGenre.keySet());

        // 장르별 인덱스 번호 부여
        HashMap<String, Integer> index = new HashMap<>(GS);
        for(int i=0; i<GS && !pq.isEmpty(); i++){
            index.put(pq.poll(), i);
        }

        // 장르별 최소 2곡씩 넣을 ArrayList. list.get(장르인덱스).get(재생횟수순위)
//        ArrayList<PriorityQueue<int []>> list = new ArrayList<>(GS);
        ArrayList<LinkedList<int []>> list = new ArrayList<>(GS);
        for (int i = 0; i < GS; i++) {
//            list.add(new PriorityQueue<>((o1, o2) -> o1[1]==o2[1]? o1[0]-o2[0]: o2[1]-o1[1]));
            list.add(new LinkedList<>());
        }

        int idx;
        int[] min = new int[GS];
        int[] cnt = new int[GS];
        for(int i=0; i<N; i++){
            idx = index.get(genres[i]);
            if(plays[i] > min[idx] || cnt[idx]<2) {
                list.get(idx).add(new int[] {i, plays[i]});
                Collections.sort(list.get(idx), (o1, o2) -> o1[1]==o2[1]? o1[0]-o2[0]: o2[1]-o1[1]);
                min[idx] = list.get(idx).getLast()[1];
                ++cnt[idx];
            } else if(plays[i] == min[idx] || cnt[idx]<3) {
                list.get(idx).add(new int[] {i, plays[i]});
                Collections.sort(list.get(idx), (o1, o2) -> o1[1]==o2[1]? o1[0]-o2[0]: o2[1]-o1[1]);
                min[idx] = list.get(idx).getLast()[1];
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        LinkedList<int[]> linkedList;
        int[] cur;

        for(idx=0; idx<GS; idx++){
            linkedList = list.get(idx);
            for(int i=0; i<2; i++) {
                cur = linkedList.poll();
                answer.add(cur[0]);
                while(!linkedList.isEmpty() && cur[1] == linkedList.peek()[1]) answer.add(linkedList.poll()[0]);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
